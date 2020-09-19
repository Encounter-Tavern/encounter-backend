package com.encountertavern.demo.controller;

import com.encountertavern.demo.repository.MonsterIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

}
