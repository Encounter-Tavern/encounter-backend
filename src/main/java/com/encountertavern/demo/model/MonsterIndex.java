package com.encountertavern.demo.model;
import javax.persistence.Entity;

import com.encountertavern.demo.enums.Aligment;
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

    @Column(name = "api_url")
    private String apiUrl;

    @Column(name = "challenge_rating")
    private double challengeRating;

    @JsonIgnore
    @OneToMany(mappedBy = "monsterIndex", targetEntity = Monster.class)
    private Set<Monster> monsters;

}
