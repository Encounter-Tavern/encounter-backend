package com.encountertavern.demo.repository;

import com.encountertavern.demo.model.MonsterIndex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonsterIndexRepository extends JpaRepository<MonsterIndex, Long> {
    List<MonsterIndex> findAll();
    List<MonsterIndex> findMonsterIndexByChallengeRatingIsLessThanEqual(double challengeRating);
}
