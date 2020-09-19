package com.encountertavern.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Encounter {

    private List<Monster> monsters;
    private String name;

}
