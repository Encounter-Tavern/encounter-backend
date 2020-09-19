package com.encountertavern.demo.model;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "encounter")
public class Encounter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "encounter_id")
    private Set<Monster> monster;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "encounter_id")
    private Set<Player> players;

}
