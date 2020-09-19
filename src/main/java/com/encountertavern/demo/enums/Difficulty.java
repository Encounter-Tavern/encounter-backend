package com.encountertavern.demo.enums;

public enum Difficulty {
    EASY(-0.5),
    NORMAL(0),
    HARD(0.5),
    TPK(1);

    private double numVal;

   private Difficulty(double numVal){
        this.numVal = numVal;
    }

    public double getNumVal(){
       return this.numVal;
    }
}
