package com.encountertavern.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EncounterDto {

    private long id;
    private String name;
    private List<MonsterDto> monsters;
    private List<PlayerDto> players;

}
