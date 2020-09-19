package com.encountertavern.demo.Models;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
    private int challengeRating;

    @Column(name = "api_url")
    private String apiUrl;

}
