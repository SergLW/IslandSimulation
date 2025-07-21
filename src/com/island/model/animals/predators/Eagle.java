package com.island.model.animals.predators;

import com.island.model.animals.Predator;

public class Eagle extends Predator {
    public Eagle() {
        super(6, 20, 3 , 1);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
