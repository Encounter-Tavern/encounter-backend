package com.encountertavern.demo.enums;

import java.util.HashMap;

public enum Aligment {
    LAWFUL_GOOD(0),
    NEUTRAL_GOOD(1),
    CHAOTIC_GOOD(2),
    LAWFUL_NEUTRAL(3),
    NEUTRAL(4),
    CHAOTIC_NEUTRAL(5),
    LAWFUL_EVIL(6),
    NEUTRAL_EVIL(7),
    CHAOTIC_EVIL(8);

    private int value;

    private Aligment(int value) {
        this.value = value;
    }

}
