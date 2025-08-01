package com.island.model.animals.predators;

import com.island.model.animals.Predator;

public class Boa extends Predator {
    public Boa() {
        super();
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
