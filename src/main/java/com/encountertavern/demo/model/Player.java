package com.encountertavern.demo.model;
import com.encountertavern.demo.dto.PlayerDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "player")
public class Player {

    public Player() {}

    public Player(PlayerDto playerDto) {
        this.id = playerDto.getId();
        this.name = playerDto.getName();
        this.level = playerDto.getLevel();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private int level;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "encounter_id", insertable = false, updatable = false)
    private Encounter encounter;

    public Player updateValues(PlayerDto playerDto) {
        this.name = playerDto.getName();
        this.level = playerDto.getLevel();
        return this;
    }

}
