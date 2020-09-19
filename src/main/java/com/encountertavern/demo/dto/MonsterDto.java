package com.encountertavern.demo.dto;

import com.encountertavern.demo.enums.Aligment;
import com.encountertavern.demo.enums.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MonsterDto {

    private long id;
    private long monsterId;
    private String index;
    private String name;
    private Type type;
    private Aligment aligment;
    @JsonProperty("armor_class")
    private int armorClass;
    private double challengeRating;
    @JsonProperty("hit_points")
    private int hitPoints;
    private int currentHitPoints;
    private String hitDice;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private String languages;
    private List<ActionDto> actions;

    @JsonProperty("damage_vulnerabilities")
    private List<String> damageVulnerabilities;
    @JsonProperty("damage_resistances")
    private List<String> damageResistances;
    @JsonProperty("damage_immunities")
    private List<String> damageImmunities;

}
