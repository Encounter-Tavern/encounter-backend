package com.encountertavern.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DamageDto {
    @JsonProperty("damage_type")
    private DamageTypeDto damageType;
    @JsonProperty("damage_dice")
    private String damageDice;
}
