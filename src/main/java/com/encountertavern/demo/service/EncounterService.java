package com.encountertavern.demo.service;

import com.encountertavern.demo.dto.EncounterDto;
import com.encountertavern.demo.dto.GenerateEncounterDto;
import com.encountertavern.demo.dto.MonsterDto;
import com.encountertavern.demo.dto.PlayerDto;
import com.encountertavern.demo.model.Encounter;
import com.encountertavern.demo.model.Monster;
import com.encountertavern.demo.model.MonsterIndex;
import com.encountertavern.demo.model.Player;
import com.encountertavern.demo.repository.EncounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class EncounterService {

    private final EncounterRepository encounterRepository;
    private final MonsterIndexService monsterIndexService;
    private final DndApiService dndApiService;
    private final MonsterService monsterService;
    private final PlayerService playerService;
    private final double difficultyIncrease = 1.5;

    @Autowired
    public EncounterService(EncounterRepository encounterRepository,
                            MonsterIndexService monsterIndexService,
                            DndApiService dndApiService,
                            MonsterService monsterService,
                            PlayerService playerService) {
        this.monsterIndexService = monsterIndexService;
        this.encounterRepository = encounterRepository;
        this.dndApiService = dndApiService;
        this.monsterService = monsterService;
        this.playerService = playerService;
    }

    public List<EncounterDto> getAll() {
        ArrayList<EncounterDto> encounterDtoList = new ArrayList<>();
        List<Encounter> encounters = encounterRepository.findAll();
        for (Encounter encounter : encounters) {
            encounterDtoList.add(convertEncounterToEncounterDto(encounter));
        }
        return encounterDtoList;
    }

    public String save(EncounterDto encounterDto) {
        encounterRepository.save(convertEncounterDtoToEncounter(encounterDto));
        return "Success";
    }

    public ResponseEntity<EncounterDto> update(Long encounterId, EncounterDto encounterDto) {
        Optional<Encounter> encounter = encounterRepository.findById(encounterId);
        if (encounter.isPresent()) {
            Encounter updatedEncounter = convertEncounterDtoToEncounter(encounterDto);
            updatedEncounter = encounterRepository.save(updatedEncounter);
            return ResponseEntity.ok(convertEncounterToEncounterDto(updatedEncounter));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<EncounterDto> getById(long encounterId) {
        Optional<Encounter> encounter = encounterRepository.findById(encounterId);
        if (encounter.isPresent()) {
            EncounterDto encounterDto = convertEncounterToEncounterDto(encounter.get());
            return ResponseEntity.ok(encounterDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
        X = Party Size
        L = average level of the party
        D = difficulty increase per level, default 2
        M = difficultyLevel monster

         X^D * L
        (-------)^2 = M^D + ... + MN^D
         4^D
     */
    public Encounter generateEncounter(GenerateEncounterDto postData) {
        Encounter encounter = new Encounter();
        encounter.setName(postData.getName());
        ArrayList<Monster> monsters = new ArrayList<>();

        double challengeRating = calculateChallengeRating(postData);
        double remainingChallengeRating = challengeRating;

        while (remainingChallengeRating < 0.5) {
            remainingChallengeRating += 0.5;
        }
        while (remainingChallengeRating >= 0.5 || monsters.size() < 1) {
            List<MonsterIndex> monsterIndexList = monsterIndexService.getWhereChallengeRatingIsLessOrEqual(Math.pow(challengeRating, 1/difficultyIncrease));
            int randomMonsterId = (int)(Math.random() * monsterIndexList.size());
            remainingChallengeRating -= Math.pow(monsterIndexList.get(randomMonsterId).getChallengeRating(), difficultyIncrease);
            Monster monster = new Monster().updateValues(dndApiService.getMonsterDto(monsterIndexList.get(randomMonsterId).getApiUrl()));
            monster.setMonsterIndex(monsterIndexList.get(randomMonsterId));
            monsters.add(monster);
        }
        encounter.setMonster(new HashSet<>(monsters));

        List<Player> players = new ArrayList<>();
        for (PlayerDto playerDto : postData.getPlayers()) {
            players.add(new Player().updateValues(playerDto));
        }
        encounter.setPlayers(new HashSet<>(players));
        return encounterRepository.save(encounter);
    }

    /**
     * X = Anzahl PC
     * Y = PC Leveldurchscnitt
     * F(x,y) = challenge_rating = y * x² / 4²
     */
    private double calculateChallengeRating(GenerateEncounterDto generateEncounterDto){
        double averagePartyLevel = 0.0;
        //Get average party level
        for(PlayerDto player: generateEncounterDto.getPlayers()){
            averagePartyLevel += player.getLevel();
        }
        averagePartyLevel = averagePartyLevel/generateEncounterDto.getPlayers().size();

        return Math.pow(
                averagePartyLevel * Math.pow(generateEncounterDto.getPlayers().size(), difficultyIncrease + generateEncounterDto.getDifficulty().getNumVal()) / Math.pow(4, difficultyIncrease),
                difficultyIncrease);
    }

    private EncounterDto convertEncounterToEncounterDto(Encounter encounter) {
        EncounterDto e = new EncounterDto();
        e.setId(encounter.getId());
        e.setName(encounter.getName());

        ArrayList<MonsterDto> monsterDtos = new ArrayList<>();
        for (Monster monster : encounter.getMonster()) {
            monsterDtos.add(monster.getMonster(dndApiService.getMonsterDto(monster.getMonsterIndex().getApiUrl())));
        }
        e.setMonsters(monsterDtos);

        ArrayList<PlayerDto> playerDtos = new ArrayList<>();
        for (Player player : encounter.getPlayers()) {
            playerDtos.add(new PlayerDto(player));
        }
        e.setPlayers(playerDtos);

        return e;
    }

    private Encounter convertEncounterDtoToEncounter(EncounterDto encounterDto) {
        Encounter e = new Encounter();
        e.setId(encounterDto.getId());
        e.setName(encounterDto.getName());

        //Set Monsters
        ArrayList<Monster> monsters = new ArrayList<>();
        for (MonsterDto monsterDto : encounterDto.getMonsters()) {
            if (monsterDto.getId() == 0) {
                Monster m = new Monster().updateValues(monsterDto);
                m.setMonsterIndex(monsterIndexService.getMonsterIndex(monsterDto.getMonsterId()));
                monsters.add(m);
            } else {
                Monster m = monsterService.getMonster(monsterDto.getId());
                monsters.add(m.updateValues(monsterDto));
            }
        }
        e.setMonster(new HashSet<>(monsters));

        //Set Players
        ArrayList<Player> players = new ArrayList<>();
        for (PlayerDto playerDto : encounterDto.getPlayers()) {
            if (playerDto.getId() == 0) {
                players.add(new Player(playerDto));
            } else {
                Player p = playerService.getPlayer(playerDto.getId());
                players.add(p.updateValues(playerDto));
            }
        }
        e.setPlayers(new HashSet<>(players));
        return e;
    }
}
