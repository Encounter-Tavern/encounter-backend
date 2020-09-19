package com.encountertavern.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Damage {
    @JsonProperty("damage_type")
    private DamageType damageType;
    @JsonProperty("damage_dice")
    private String damageDice;
}
