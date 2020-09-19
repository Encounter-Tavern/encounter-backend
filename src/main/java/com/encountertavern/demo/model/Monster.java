package com.encountertavern.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "monster")
public class Monster {

    public com.encountertavern.demo.dto.Monster getMonster(com.encountertavern.demo.dto.Monster monster) {
        monster.setId(this.id);
        monster.setName(this.name);
        monster.setHitPoints(this.hitPoints);
        monster.setCurrentHitPoints(this.currentHitPoints);
        monster.setMonsterId(this.monsterIndex.getId());

        monster.setStrength(this.strength);
        monster.setDexterity(this.dexterity);
        monster.setConstitution(this.constitution);
        monster.setIntelligence(this.intelligence);
        monster.setWisdom(this.wisdom);
        monster.setCharisma(this.charisma);
        return monster;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "hit_points")
    private int hitPoints;

    @Column(name = "current_hit_points")
    private int currentHitPoints;

    @Column(name = "strength")
    private int strength;

    @Column(name = "dexterity")
    private int dexterity;

    @Column(name = "constitution")
    private int constitution;

    @Column(name = "intelligence")
    private int intelligence;

    @Column(name = "wisdom")
    private int wisdom;

    @Column(name = "charisma")
    private int charisma;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "encounter_id", insertable = false, updatable = false)
    private Encounter encounter;

    @ManyToOne
    @JoinColumn(name = "monster_index_id", nullable = false)
    private MonsterIndex monsterIndex;

    public Monster updateValues(com.encountertavern.demo.dto.Monster monster) {
        this.name = monster.getName();
        this.hitPoints = monster.getHitPoints();
        this.currentHitPoints = monster.getCurrentHitPoints();

        this.strength = monster.getStrength();
        this.dexterity = monster.getDexterity();
        this.constitution = monster.getConstitution();
        this.intelligence = monster.getIntelligence();
        this.wisdom = monster.getIntelligence();
        this.charisma = monster.getCharisma();
        return this;
    }

}
