package com.encountertavern.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema
public class ActionDto {

    @Schema(required = true,
            description = "The name of this action",
            example="Shortbow")
    private String name;

    @Schema(required = true,
            description = "Description of this action",
            example="Ranged Weapon Attack: +4 to hit, range 80/320 ft., one target. Hit: 5 (1d6 + 2) piercing damage.")
    private String desc;

    @Schema(required = true,
            description = "The bonus for the attack roll",
            example="4")
    @JsonProperty("attack_bonus")
    private int attackBonus;

    @Schema(required = true,
            description = "What damage this action causes")
    private List<DamageDto> damage;
}
