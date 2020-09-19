package com.encountertavern.demo.Models;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "monster")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "hit_points")
    private int hitPoints;

    @ManyToOne
    @JoinColumn(name = "encounter_id", insertable = false, updatable = false)
    private Encounter encounter;

    @ManyToOne
    @JoinColumn(name = "monster_index_id", insertable = false, updatable = false)
    private MonsterIndex monsterIndex;

}
