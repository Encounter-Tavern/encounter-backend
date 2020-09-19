package com.encountertavern.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonsterIndexRepository extends JpaRepository<MonsterIndex, Long> {
    List<MonsterIndex> findAll();
}
