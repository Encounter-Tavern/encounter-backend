package com.encountertavern.demo;

import com.encountertavern.demo.Models.*;
import com.encountertavern.demo.enums.DamageType;
import com.encountertavern.demo.enums.Difficulty;
import com.encountertavern.demo.enums.Language;
import com.encountertavern.demo.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class AttributeController {

    private MonsterIndexRepository monsterIndexRepository;
    private EncounterRepository encounterRepository;
    private MonsterRepository monsterRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public AttributeController(MonsterIndexRepository monsterIndexRepository,
                               EncounterRepository encounterRepository,
                               MonsterRepository monsterRepository,
                               PlayerRepository playerRepository) {
        this.monsterIndexRepository = monsterIndexRepository;
        this.encounterRepository = encounterRepository;
        this.monsterRepository = monsterRepository;
        this.playerRepository = playerRepository;
    }

    @RequestMapping("/test")
    public List<Encounter> test() {
        MonsterIndex mi = new MonsterIndex();
        mi.setName("Goblin");
        mi.setApiUrl("TESTURL");
        mi.setChallengeRating(1);
        mi.setDefaultHitPoints(7);
        monsterIndexRepository.save(mi);

        Monster m1 = new Monster();
        m1.setName("Boblin");
        Monster m2 = new Monster();
        m2.setName("Goblin");
        Set<Monster> m = Set.of(new Monster[]{m1, m2});

        Player p1 = new Player();
        p1.setLevel(5);
        p1.setName("Glim");
        Set<Player> p = Set.of(new Player[]{p1});

        Encounter testEncounter = new Encounter();
        testEncounter.setName("TPK");
        testEncounter.setMonster(m);
        testEncounter.setPlayers(p);

        encounterRepository.save(testEncounter);
        m2.setName("BOB");
        monsterRepository.save(m2);
        //encounterRepository.save(testEncounter);
        return encounterRepository.findAll();
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
