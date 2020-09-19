package com.encountertavern.demo.controller;

import com.encountertavern.demo.dto.EncounterDto;
import com.encountertavern.demo.dto.GenerateEncounterDto;
import com.encountertavern.demo.dto.MonsterDto;
import com.encountertavern.demo.dto.PlayerDto;
import com.encountertavern.demo.model.*;
import com.encountertavern.demo.repository.EncounterRepository;
import com.encountertavern.demo.repository.MonsterIndexRepository;
import com.encountertavern.demo.repository.MonsterRepository;
import com.encountertavern.demo.repository.PlayerRepository;
import com.encountertavern.demo.service.EncounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class EncounterController {

    private final MonsterIndexRepository monsterIndexRepository;
    private final MonsterRepository monsterRepository;
    private final PlayerRepository playerRepository;
    private final EncounterRepository encounterRepository;
    private final RestTemplate restTemplate;
    private final EncounterService encounterService;

    @Value("${5e-srd-api.url}")
    private String dndApiUrl;

    @Autowired
    public EncounterController(MonsterIndexRepository monsterIndexRepository,
                               MonsterRepository monsterRepository,
                               PlayerRepository playerRepository,
                               EncounterRepository encounterRepository,
                               RestTemplateBuilder restTemplateBuilder,
                               EncounterService encounterService) {
        this.monsterIndexRepository = monsterIndexRepository;
        this.monsterRepository = monsterRepository;
        this.playerRepository = playerRepository;
        this.encounterRepository = encounterRepository;
        this.restTemplate = restTemplateBuilder.build();
        this.encounterService = encounterService;
    }

    @RequestMapping(value = "/encounters", method = RequestMethod.GET)
    public List<EncounterDto> getEncounters() {
        ArrayList<EncounterDto> encounterDtoList = new ArrayList<>();
        List<Encounter> encounters = encounterRepository.findAll();
        for (Encounter encounter: encounters) {
            encounterDtoList.add(getEncounterDtoFromModel(encounter));
        }
        return encounterDtoList;
    }

    @RequestMapping(value = "/encounters", method = RequestMethod.POST)
    public String postEncounter(@RequestBody EncounterDto encounterDto) {
        encounterRepository.save(getEncounterModelFromDTO(encounterDto));
        return "Success";
    }

    @RequestMapping(value = "/encounters/{encounterId}", method = RequestMethod.GET)
    public EncounterDto getEncounter(@PathVariable Long encounterId) {
        try {
            Encounter encounter = encounterRepository.getOne(encounterId);
            if (encounter.getId() == null) {
                return null;
            }
            return getEncounterDtoFromModel(encounter);
        } catch (Exception exception) {
            return null;
        }
    }

    @RequestMapping(value = "/encounters/{encounterId}", method = RequestMethod.PUT)
    public String putEncounter(@PathVariable Long encounterId, @RequestBody EncounterDto encounterDto) {
        encounterRepository.save(getEncounterModelFromDTO(encounterDto));
        return "Success";
    }

    @RequestMapping(value = "/encounters/generate", method = RequestMethod.POST)
    public long generateEncounter(@RequestBody GenerateEncounterDto encounterDto) {
        return this.encounterService.generateEncounter(encounterDto).getId();
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

    private EncounterDto getEncounterDtoFromModel(Encounter encounter) {
        EncounterDto e = new EncounterDto();
        e.setId(encounter.getId());
        e.setName(encounter.getName());

        ArrayList<MonsterDto> monsterDtos = new ArrayList<>();
        for (Monster monster: encounter.getMonster()) {
            monsterDtos.add(monster.getMonster(restTemplate.getForObject(dndApiUrl + "monsters/" + monster.getMonsterIndex().getApiUrl(), MonsterDto.class)));
        }
        e.setMonsters(monsterDtos);

        ArrayList<PlayerDto> playerDtos = new ArrayList<>();
        for (Player player: encounter.getPlayers()) {
            playerDtos.add(new PlayerDto(player));
        }
        e.setPlayers(playerDtos);

        return e;
    }

}
