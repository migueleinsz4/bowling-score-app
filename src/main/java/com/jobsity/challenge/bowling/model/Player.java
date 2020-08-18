package com.jobsity.challenge.bowling.model;

import lombok.Getter;

@Getter
public class Player {
    private final int number;
    private final String name;

    public Player(int number, String name) {
        this.number = number;
        this.name = name;
    }
}