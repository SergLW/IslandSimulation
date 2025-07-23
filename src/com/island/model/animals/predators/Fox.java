package com.island.model.animals.predators;

import com.island.model.animals.Predator;

public class Fox extends Predator {

    public Fox() {
        super();
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
