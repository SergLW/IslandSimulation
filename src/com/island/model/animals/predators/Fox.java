package com.island.model.animals.predators;

import com.island.model.animals.Predator;

public class Fox extends Predator {

    public Fox() {
        super(8, 30, 2, 2);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
