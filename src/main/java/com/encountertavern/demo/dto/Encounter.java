package com.encountertavern.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Encounter {

    private long id;
    private String name;
    private List<Monster> monsters;
    private List<Player> players;

}
