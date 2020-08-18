package com.jobsity.challenge.bowling.model;

import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Getter
public class PlayerScore<T extends BasicFrame> {
    private Player player;
    private List<T> frames;

    public PlayerScore(Player player, List<T> frames) {
        this.player = player;
        this.frames = frames;
    }

    public Integer lastScore() {
        Optional <T> lastFrame = Optional.ofNullable(CollectionUtils.lastElement(this.frames));
        if (lastFrame.isPresent()) {
            return lastFrame.get().currentScore();
        } else {
            return 0;
        }
    }
}