package com.encountertavern.demo.enums;

public enum Difficulty {
    EASY(-0.1),
    NORMAL(0.025),
    HARD(0.1),
    TPK(0.25);

    private double numVal;

   private Difficulty(double numVal){
        this.numVal = numVal;
    }

    public double getNumVal(){
       return this.numVal;
    }
}
