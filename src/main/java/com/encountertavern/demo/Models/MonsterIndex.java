package com.encountertavern.demo.Models;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "monster_index")
public class MonsterIndex implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "default_hit_points")
    private int defaultHitPoints;

    @Column(name = "challenge_rating")
    private double challengeRating;

    @Column(name = "api_url")
    private String apiUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "monsterIndex", targetEntity = Monster.class)
    private Set<Monster> monsters;

}
