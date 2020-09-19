package com.encountertavern.demo.dto;

import com.encountertavern.demo.enums.Aligment;
import com.encountertavern.demo.enums.Language;
import com.encountertavern.demo.enums.DamageType;
import com.encountertavern.demo.enums.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Monster {

    private long id;
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

    @JsonIgnore
    private List<Language> languages;
    private List<Action> actions;

    private List<DamageType> damageVulnerabilities;
    private List<DamageType> damageResistances;

}
