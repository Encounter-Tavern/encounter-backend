package com.encountertavern.demo;

import com.encountertavern.demo.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @RequestMapping(value = "/encounters")
    public List<com.encountertavern.demo.dto.Encounter> getEncounters() {
        ArrayList<com.encountertavern.demo.dto.Encounter> encounterList = new ArrayList<>();
        List<Encounter> encounters = encounterRepository.findAll();
        for (Encounter encounter: encounters) {
            com.encountertavern.demo.dto.Encounter e = new com.encountertavern.demo.dto.Encounter();
            encounterList.add(e);

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
        }
        return encounterList;
    }

    //todo
    /*
    Create insert script to add the monsters to the db
    Test get encounters

    write method to save encounter
    write method to get 1 encounter
    write method to update encounter
     */



}
