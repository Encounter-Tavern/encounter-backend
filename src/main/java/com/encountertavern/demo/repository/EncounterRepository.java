package com.encountertavern.demo.repository;

import com.encountertavern.demo.model.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EncounterRepository extends JpaRepository<Encounter, Long> {
    List<Encounter> findAll();
}
