package com.encountertavern.demo.enums;

public enum Type {
    abberation("abberaation"),
    best("beast"),
    celestial("celestial"),
    construct("construct"),
    dragon("dragon"),
    elemental("elemental"),
    fey("fey"),
    fiend("fiend"),
    giant("giant"),
    humanoid("humanoid"),
    monstrosity("monstrosity"),
    ooze("ooze"),
    plant("plant"),
    swarm_of_tiny_beasts("swarm_of_tiny_beasts"),
    undead("undead");

    public final String label;

    private Type(String label) {
        this.label = label;
    }

}
