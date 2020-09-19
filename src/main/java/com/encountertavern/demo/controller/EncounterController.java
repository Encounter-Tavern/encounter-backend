package com.encountertavern.demo.controller;

import com.encountertavern.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class EncounterController {

    private MonsterIndexRepository monsterIndexRepository;
    private MonsterRepository monsterRepository;
    private PlayerRepository playerRepository;
    private EncounterRepository encounterRepository;
    private final RestTemplate restTemplate;

    @Value("${5e-srd-api.url}")
    private String dndApiUrl;

    @Autowired
    public EncounterController(MonsterIndexRepository monsterIndexRepository,
                               MonsterRepository monsterRepository,
                               PlayerRepository playerRepository,
                               EncounterRepository encounterRepository,
                               RestTemplateBuilder restTemplateBuilder) {
        this.monsterIndexRepository = monsterIndexRepository;
        this.monsterRepository = monsterRepository;
        this.playerRepository = playerRepository;
        this.encounterRepository = encounterRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping(value = "/encounters", method = RequestMethod.GET)
    public List<com.encountertavern.demo.dto.Encounter> getEncounters() {
        ArrayList<com.encountertavern.demo.dto.Encounter> encounterList = new ArrayList<>();
        List<Encounter> encounters = encounterRepository.findAll();
        for (Encounter encounter: encounters) {
            encounterList.add(getEncounterDtoFromModel(encounter));
        }
        return encounterList;
    }

    @RequestMapping(value = "/encounters", method = RequestMethod.POST)
    public String postEncounter(@RequestBody com.encountertavern.demo.dto.Encounter encounter) {
        encounterRepository.save(getEncounterModelFromDTO(encounter));
        return "Success";
    }

    @RequestMapping(value = "/encounters/{encounterId}", method = RequestMethod.GET)
    public com.encountertavern.demo.dto.Encounter getEncounter(@PathVariable Long encounterId) {
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
    public String putEncounter(@PathVariable Long encounterId, @RequestBody com.encountertavern.demo.dto.Encounter encounter) {
        encounterRepository.save(getEncounterModelFromDTO(encounter));
        return "Success";
    }

    private Encounter getEncounterModelFromDTO(com.encountertavern.demo.dto.Encounter encounter) {
        Encounter e = new Encounter();
        e.setId(encounter.getId());
        e.setName(encounter.getName());

        //Set Monsters
        ArrayList<Monster> monsters = new ArrayList<>();
        for (com.encountertavern.demo.dto.Monster monster: encounter.getMonsters()) {
            if (monster.getMonsterId() == 0) {
                Monster m = new Monster(monster);
                m.setMonsterIndex(monsterIndexRepository.getOne(monster.getMonsterId()));
                monsters.add(m);
            } else {
                Monster m = monsterRepository.getOne(monster.getId());
                monsters.add(m.updateValues(monster));
            }
        }
        e.setMonster(new HashSet<>(monsters));

        //Set Players
        ArrayList<Player> players = new ArrayList<>();
        for (com.encountertavern.demo.dto.Player player: encounter.getPlayers()) {
            if (player.getId() == 0) {
                players.add(new Player(player));
            } else {
                Player p = playerRepository.getOne(player.getId());
                players.add(p.updateValues(player));
            }
        }
        e.setPlayers(new HashSet<>(players));
        return e;
    }

    private com.encountertavern.demo.dto.Encounter getEncounterDtoFromModel(Encounter encounter) {
        com.encountertavern.demo.dto.Encounter e = new com.encountertavern.demo.dto.Encounter();
        e.setId(encounter.getId());
        e.setName(encounter.getName());

        ArrayList<com.encountertavern.demo.dto.Monster> monsters = new ArrayList<>();
        for (Monster monster: encounter.getMonster()) {
            monsters.add(monster.getMonster(restTemplate.getForObject(dndApiUrl + "monsters/" + monster.getMonsterIndex().getApiUrl(), com.encountertavern.demo.dto.Monster.class)));
        }
        e.setMonsters(monsters);

        ArrayList<com.encountertavern.demo.dto.Player> players = new ArrayList<>();
        for (Player player: encounter.getPlayers()) {
            players.add(new com.encountertavern.demo.dto.Player(player));
        }
        e.setPlayers(players);

        return e;
    }

}
