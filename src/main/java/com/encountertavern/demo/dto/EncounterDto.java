package com.encountertavern.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema
public class EncounterDto {

    @Schema(required = true,
            description = "Unique identifier",
            example = "17")
    private long id;

    @Schema(required = true,
            description = "The name of this encounter",
            example = "Forest ambush")
    private String name;

    @Schema(required = true,
            description = "The monsters present in this encounter")
    private List<MonsterDto> monsters;

    @Schema(required = true,
            description = "The players present in this encounter")
    private List<PlayerDto> players;

}
