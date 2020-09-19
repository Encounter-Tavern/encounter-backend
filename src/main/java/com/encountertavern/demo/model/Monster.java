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

    public Monster() {}

    public Monster(com.encountertavern.demo.dto.Monster monster) {
        this.id = monster.getId();
        this.name = monster.getName();
        this.hitPoints = monster.getHitPoints();
        this.currentHitPoints = monster.getCurrentHitPoints();
    }

    public com.encountertavern.demo.dto.Monster getMonster(com.encountertavern.demo.dto.Monster monster) {
        monster.setId(this.id);
        monster.setName(this.name);
        monster.setHitPoints(this.hitPoints);
        monster.setCurrentHitPoints(this.currentHitPoints);
        monster.setMonsterId(monsterIndex.getId());
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "encounter_id", insertable = false, updatable = false)
    private Encounter encounter;

    @ManyToOne
    @JoinColumn(name = "monster_index_id", nullable = false)
    private MonsterIndex monsterIndex;

}
