package com.encountertavern.demo.repository;

import com.encountertavern.demo.model.Monster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonsterRepository extends JpaRepository<Monster, Long> {
    List<Monster> findAll();
}
