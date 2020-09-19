package com.encountertavern.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    public Player() {}

    public Player(com.encountertavern.demo.model.Player player) {
        this.name = player.getName();
        this.level = player.getLevel();
    }

    private String name;
    private int level;

}
