package com.encountertavern.demo.controller;

import com.encountertavern.demo.enums.DamageType;
import com.encountertavern.demo.enums.Difficulty;
import com.encountertavern.demo.enums.Language;
import com.encountertavern.demo.enums.Type;
import com.encountertavern.demo.repository.EncounterRepository;
import com.encountertavern.demo.repository.MonsterIndexRepository;
import com.encountertavern.demo.repository.MonsterRepository;
import com.encountertavern.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AttributeController {

    @RequestMapping("/languages")
    public Language[] getLanguages() {
        return Language.values();
    }

    @RequestMapping("/difficulties")
    public Difficulty[] getDifficulties() {
        return Difficulty.values();
    }

    @RequestMapping("/damage-types")
    public DamageType[] getDamageTypes() {
        return DamageType.values();
    }

    @RequestMapping("/types")
    public Type[] getTypes() {
        return Type.values();
    }

}
