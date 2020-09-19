package com.encountertavern.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonsterRepository extends JpaRepository<Monster, Long> {
    List<Monster> findAll();
}
