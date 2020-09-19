package com.encountertavern.demo.service;

import com.encountertavern.demo.dto.EncounterDto;
import com.encountertavern.demo.dto.GenerateEncounterDto;
import com.encountertavern.demo.dto.MonsterDto;
import com.encountertavern.demo.dto.PlayerDto;
import com.encountertavern.demo.model.*;
import com.encountertavern.demo.repository.EncounterRepository;
import com.encountertavern.demo.repository.MonsterIndexRepository;
import com.encountertavern.demo.repository.MonsterRepository;
import com.encountertavern.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class EncounterService {

    private final MonsterIndexRepository monsterIndexRepository;
    private final EncounterRepository encounterRepository;
    private final DndApiService dndApiService;
    private final MonsterRepository monsterRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public EncounterService(MonsterIndexRepository monsterIndexRepository,
                            EncounterRepository encounterRepository,
                            DndApiService dndApiService,
                            MonsterRepository monsterRepository,
                            PlayerRepository playerRepository){
        this.monsterIndexRepository = monsterIndexRepository;
        this.encounterRepository = encounterRepository;
        this.dndApiService = dndApiService;
        this.monsterRepository = monsterRepository;
        this.playerRepository = playerRepository;
    }

    public List<EncounterDto> getAll(){
        ArrayList<EncounterDto> encounterDtoList = new ArrayList<>();
        List<Encounter> encounters = encounterRepository.findAll();
        for (Encounter encounter: encounters) {
            encounterDtoList.add(getEncounterDtoFromModel(encounter));
        }
        return encounterDtoList;
    }

    public String save(EncounterDto encounterDto){
        encounterRepository.save(getEncounterModelFromDTO(encounterDto));
        return "Success";
    }

    public Encounter generateEncounter(GenerateEncounterDto postData){
        Encounter encounter = new Encounter();
        encounter.setName(postData.getName());

        List<MonsterIndex> monsterIndexList = monsterIndexRepository.findAll();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < postData.getPlayers().size(); i++) {
            int index = (int)(Math.random() * monsterIndexList.size());
            Monster m = new Monster().updateValues(dndApiService.getMonsterDto(monsterIndexList.get(index).getApiUrl()));
            m.setMonsterIndex(monsterIndexList.get(index));
            monsters.add(m);
        }
        encounter.setMonster(new HashSet<>(monsters));

        List<Player> players = new ArrayList<>();
        for (PlayerDto playerDto : postData.getPlayers()) {
            players.add(new Player().updateValues(playerDto));
        }
        encounter.setPlayers(new HashSet<>(players));
        return encounterRepository.save(encounter);
    }

    private EncounterDto getEncounterDtoFromModel(Encounter encounter) {
        EncounterDto e = new EncounterDto();
        e.setId(encounter.getId());
        e.setName(encounter.getName());

        ArrayList<MonsterDto> monsterDtos = new ArrayList<>();
        for (Monster monster: encounter.getMonster()) {
            monsterDtos.add(monster.getMonster(dndApiService.getMonsterDto(monster.getMonsterIndex().getApiUrl())));
        }
        e.setMonsters(monsterDtos);

        ArrayList<PlayerDto> playerDtos = new ArrayList<>();
        for (Player player: encounter.getPlayers()) {
            playerDtos.add(new PlayerDto(player));
        }
        e.setPlayers(playerDtos);

        return e;
    }

    private Encounter getEncounterModelFromDTO(EncounterDto encounterDto) {
        Encounter e = new Encounter();
        e.setId(encounterDto.getId());
        e.setName(encounterDto.getName());

        //Set Monsters
        ArrayList<Monster> monsters = new ArrayList<>();
        for (MonsterDto monsterDto : encounterDto.getMonsters()) {
            if (monsterDto.getId() == 0) {
                Monster m = new Monster().updateValues(monsterDto);
                m.setMonsterIndex(monsterIndexRepository.getOne(monsterDto.getMonsterId()));
                monsters.add(m);
            } else {
                Monster m = monsterRepository.getOne(monsterDto.getId());
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
                Player p = playerRepository.getOne(playerDto.getId());
                players.add(p.updateValues(playerDto));
            }
        }
        e.setPlayers(new HashSet<>(players));
        return e;
    }
}
