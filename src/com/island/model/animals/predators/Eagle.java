package com.island.model.animals.predators;

import com.island.model.animals.Predator;

public class Eagle extends Predator {
    public Eagle() {
        super();
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
