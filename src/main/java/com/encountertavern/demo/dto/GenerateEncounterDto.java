package com.encountertavern.demo.dto;

import com.encountertavern.demo.enums.Difficulty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "Can be used to quickly generate a new encounter")
public class GenerateEncounterDto {

    @Schema(required = true,
            description = "The name of this to be generated encounter",
            example = "Forest ambush")
    private String name;

    @Schema(required = true,
            description = "List of players that are present in this to be generated encounter")
    private List<PlayerDto> players;

    @Schema(required = true,
            description = "Difficulty level of the to be generated encounter",
            example = "NORMAL")
    private Difficulty difficulty;

}
