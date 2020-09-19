package com.encountertavern.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class DamageDto {

    @Schema(required = true,
            description = "The type of damage")
    @JsonProperty("damage_type")
    private DamageTypeDto damageType;

    @Schema(required = true,
            description = "How this damage is represented in dice rolls",
            example = "1d6+2")
    @JsonProperty("damage_dice")
    private String damageDice;
}
