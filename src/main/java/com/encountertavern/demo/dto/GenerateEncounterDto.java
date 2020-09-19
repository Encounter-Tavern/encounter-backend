package com.encountertavern.demo.dto;

import com.encountertavern.demo.enums.Difficulty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenerateEncounterDto {

    private String name;
    private List<PlayerDto> players;
    private Difficulty difficulty;

}
