package com.encountertavern.demo.service;

import com.encountertavern.demo.model.Player;
import com.encountertavern.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayer(long id){
        return playerRepository.getOne(id);
    }
}
