package com.encountertavern.demo.model;
import com.encountertavern.demo.dto.MonsterDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "monster")
public class Monster {

    public MonsterDto getMonster(MonsterDto monsterDto) {
        monsterDto.setId(this.id);
        monsterDto.setName(this.name);
        monsterDto.setHitPoints(this.hitPoints);
        monsterDto.setCurrentHitPoints(this.currentHitPoints);
        monsterDto.setMonsterId(this.monsterIndex.getId());

        monsterDto.setStrength(this.strength);
        monsterDto.setDexterity(this.dexterity);
        monsterDto.setConstitution(this.constitution);
        monsterDto.setIntelligence(this.intelligence);
        monsterDto.setWisdom(this.wisdom);
        monsterDto.setCharisma(this.charisma);
        return monsterDto;
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

    public Monster updateValues(MonsterDto monsterDto) {
        this.name = monsterDto.getName();
        this.hitPoints = monsterDto.getHitPoints();
        this.currentHitPoints = monsterDto.getCurrentHitPoints();

        this.strength = monsterDto.getStrength();
        this.dexterity = monsterDto.getDexterity();
        this.constitution = monsterDto.getConstitution();
        this.intelligence = monsterDto.getIntelligence();
        this.wisdom = monsterDto.getIntelligence();
        this.charisma = monsterDto.getCharisma();
        return this;
    }

}
