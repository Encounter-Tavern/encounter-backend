package com.encountertavern.demo.service;

import com.encountertavern.demo.dto.MonsterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DndApiService {

    @Value("${5e-srd-api.url}")
    private String dndApiUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public DndApiService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.rootUri(dndApiUrl).build();
    }

    public MonsterDto getMonsterDto(String monsterUrl){
        return restTemplate.getForObject("/monsters" + monsterUrl, MonsterDto.class);
    }
}
