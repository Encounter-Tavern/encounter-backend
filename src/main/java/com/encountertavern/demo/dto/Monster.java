package com.encountertavern.demo.dto;

import com.encountertavern.demo.enums.Aligment;
import com.encountertavern.demo.enums.Language;
import com.encountertavern.demo.enums.DamageType;
import com.encountertavern.demo.enums.Type;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Monster {

    private long id;
    private long monsterId;
    private String index;
    private String name;
    private Type type;
    private Aligment aligment;
    private int armorClass;
    private double challengeRating;
    private int hitPoints;
    private int currentHitPoints;
    private String hitDice;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private List<String> languages;
    private List<Action> actions;

    @JsonProperty("damage_vulnerabilities")
    private List<String> damageVulnerabilities;
    @JsonProperty("damage_resistances")
    private List<String> damageResistances;
    @JsonProperty("damage_immunities")
    private List<String> damageImmunities;

}
