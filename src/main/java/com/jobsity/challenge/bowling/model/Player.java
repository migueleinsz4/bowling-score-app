package com.jobsity.challenge.bowling.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }
}