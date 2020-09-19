package com.encountertavern.demo.controller;

import com.encountertavern.demo.model.*;
import com.encountertavern.demo.enums.DamageType;
import com.encountertavern.demo.enums.Difficulty;
import com.encountertavern.demo.enums.Language;
import com.encountertavern.demo.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@RestController
public class AttributeController {

    private MonsterIndexRepository monsterIndexRepository;
    private EncounterRepository encounterRepository;
    private MonsterRepository monsterRepository;
    private PlayerRepository playerRepository;
    private final RestTemplate restTemplate;

    @Value("${5e-srd-api.url}")
    private String dndApiUrl;

    @Autowired
    public AttributeController(MonsterIndexRepository monsterIndexRepository,
                               EncounterRepository encounterRepository,
                               MonsterRepository monsterRepository,
                               PlayerRepository playerRepository,
                               RestTemplateBuilder restTemplateBuilder) {
        this.monsterIndexRepository = monsterIndexRepository;
        this.encounterRepository = encounterRepository;
        this.monsterRepository = monsterRepository;
        this.playerRepository = playerRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping("/languages")
    public Language[] getLanguages() {
        return Language.values();
    }

    @RequestMapping("/difficulties")
    public Difficulty[] getDifficulties() {
        return Difficulty.values();
    }

    @RequestMapping("/damagetypes")
    public DamageType[] getDamageTypes() {
        return DamageType.values();
    }

    @RequestMapping("/types")
    public Type[] getTypes() {
        return Type.values();
    }

}
