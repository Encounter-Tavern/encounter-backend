package com.encountertavern.demo.controller;

import com.encountertavern.demo.dto.Monster;
import com.encountertavern.demo.model.MonsterIndex;
import com.encountertavern.demo.model.MonsterIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    private final MonsterIndexRepository monsterIndexRepository;
    private final RestTemplate restTemplate;

    @Value("${5e-srd-api.url}")
    private String dndApiUrl;

    @Autowired
    public TestController(MonsterIndexRepository monsterIndexRepository, RestTemplateBuilder restTemplateBuilder) {
        this.monsterIndexRepository = monsterIndexRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping("/test")
    public List<Monster> test() {
        List<MonsterIndex> monsterIndexList = monsterIndexRepository.findAll();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (MonsterIndex monsterIndex: monsterIndexList) {
            System.out.println(monsterIndex.getApiUrl());
            monsters.add(restTemplate.getForObject(dndApiUrl + "monsters/" + monsterIndex.getApiUrl(), Monster.class));
        }
        return monsters;
    }

}
