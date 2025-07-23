package com.island.model.animals.predators;

import com.island.model.animals.Predator;

public class Bear extends Predator {
    public Bear() {
        super();
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
