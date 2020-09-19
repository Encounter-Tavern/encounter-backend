package com.encountertavern.demo.repository;

import com.encountertavern.demo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAll();
}
