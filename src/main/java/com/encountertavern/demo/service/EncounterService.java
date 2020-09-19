package com.encountertavern.demo.service;

import com.encountertavern.demo.dto.GenerateEncounterDto;
import com.encountertavern.demo.dto.PlayerDto;
import com.encountertavern.demo.model.*;
import com.encountertavern.demo.repository.EncounterRepository;
import com.encountertavern.demo.repository.MonsterIndexRepository;
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

    @Autowired
    public EncounterService(MonsterIndexRepository monsterIndexRepository,
                            EncounterRepository encounterRepository,
                            DndApiService dndApiService){
        this.monsterIndexRepository = monsterIndexRepository;
        this.encounterRepository = encounterRepository;
        this.dndApiService = dndApiService;
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
}
