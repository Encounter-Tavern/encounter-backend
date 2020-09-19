package com.encountertavern.demo.service;

import com.encountertavern.demo.model.MonsterIndex;
import com.encountertavern.demo.repository.MonsterIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterIndexService {
    private final MonsterIndexRepository monsterIndexRepository;

    @Autowired
    public MonsterIndexService(MonsterIndexRepository monsterIndexRepository) {
        this.monsterIndexRepository = monsterIndexRepository;
    }

    public List<MonsterIndex> getAll(){
        return monsterIndexRepository.findAll();
    }

    public MonsterIndex getMonsterIndex(long id){
        return monsterIndexRepository.getOne(id);
    }

}
