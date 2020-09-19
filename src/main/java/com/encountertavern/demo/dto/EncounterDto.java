package com.encountertavern.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EncounterDto {

    private long id;
    private String name;
    private List<MonsterDto> monsters;
    private List<PlayerDto> players;

}
