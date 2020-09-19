package com.encountertavern.demo.dto;

import com.encountertavern.demo.enums.Aligment;
import com.encountertavern.demo.enums.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema
public class MonsterDto {

    @Schema(required = true,
            description = "Unique identifier for this instance of a monster",
            example = "12")
    private long id;

    @Schema(required = true,
            description = "Id to the corresponding monster",
            example = "11")
    private long monsterId;

    @Schema(required = true,
            description = "Index to the dnd5eapi endpoint of this monster",
            example = "goblin",
            externalDocs = @ExternalDocumentation(url = "https://www.dnd5eapi.co"))
    private String index;

    @Schema(required = true,
            description = "The name of this individual monster",
            example = "Boblin the Goblin")
    private String name;

    @Schema(required = true,
            description = "To which type of being this monster belongs",
            example = "humanoid")
    private Type type;

    @Schema(required = true,
            description = "The alignment of this monster",
            example="Neutral Evil")
    private Aligment aligment;

    @Schema(required = true,
            description = "The armor class of this monster",
            example="15")
    @JsonProperty("armor_class")
    private int armorClass;

    @Schema(required = true,
            description = "The challenge rating of this monster",
            example="0.25",
            externalDocs = @ExternalDocumentation(url = "https://www.nerdsandscoundrels.com/how-to-calculate-cr-5e/"))
    private double challengeRating;

    @Schema(required = true,
            description = "Reference point for the hit points",
            example="7")
    @JsonProperty("hit_points")
    private int hitPoints;

    @Schema(required = true,
            description = "The current hitpoints of this individual monster",
            example="5")
    private int currentHitPoints;

    @Schema(required = true,
            description = "Hit die of this monster",
            example="2d6")
    @JsonProperty("hit_dice")
    private String hitDice;

    @Schema(required = true,
            description = "The strength of this monster",
            example="8")
    private int strength;

    @Schema(required = true,
            description = "The dexterity of this monster",
            example="14")
    private int dexterity;

    @Schema(required = true,
            description = "The constitution of this monster",
            example="10")
    private int constitution;

    @Schema(required = true,
            description = "The intelligence of this monster",
            example="10")
    private int intelligence;

    @Schema(required = true,
            description = "The wisdom of this monster",
            example="8")
    private int wisdom;

    @Schema(required = true,
            description = "The charisma of this monster",
            example="8")
    private int charisma;

    @Schema(required = true,
            description = "All the languages that this monster can communicate in a comma seperate manner",
            example = "Common, Goblin"
    )
    private String languages;

    @Schema(required = true,
            description = "All the actions this monster can execute")
    private List<ActionDto> actions;

    @Schema(required = true,
            description = "All the damage types against which this monster is vulnerable")
    @JsonProperty("damage_vulnerabilities")
    private List<String> damageVulnerabilities;

    @Schema(required = true,
            description = "All the damage types against which this monster has resistance")
    @JsonProperty("damage_resistances")
    private List<String> damageResistances;

    @Schema(required = true,
            description = "All the damage types against which this monster is immune")
    @JsonProperty("damage_immunities")
    private List<String> damageImmunities;

}
