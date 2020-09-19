package com.encountertavern.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    public Player() {}

    public Player(com.encountertavern.demo.model.Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.level = player.getLevel();
    }

    private long id;
    private String name;
    private int level;

}
