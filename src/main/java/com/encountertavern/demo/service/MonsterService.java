package com.encountertavern.demo.service;

import com.encountertavern.demo.model.Monster;
import com.encountertavern.demo.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterService {
    private final MonsterRepository monsterRepository;

    @Autowired
    public MonsterService(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    public Monster getMonster(long id){
        return monsterRepository.getOne(id);
    }
}
