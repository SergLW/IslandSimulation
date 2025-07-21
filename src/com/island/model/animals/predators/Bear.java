package com.island.model.animals.predators;

import com.island.model.animals.Predator;

public class Bear extends Predator {
    public Bear() {
        super(500, 5, 2, 80);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
