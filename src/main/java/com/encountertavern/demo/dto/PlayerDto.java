package com.encountertavern.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class PlayerDto {

    public PlayerDto() {}

    public PlayerDto(com.encountertavern.demo.model.Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.level = player.getLevel();
    }

    @Schema(required = true,
            description = "Unique identifier",
            example = "67")
    private long id;
    @Schema(required = true,
            description = "Name of the player character",
            example = "Hilbert the Halfling"
    )
    private String name;
    @Schema(required = true,
            description = "The current level of the player",
            example = "2"
    )
    private int level;

}
