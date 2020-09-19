package com.encountertavern.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Action {

    private String name;
    private String desc;
    @JsonProperty("attack_bonus")
    private int attackBonus;
    private List<Damage> damage;
}
