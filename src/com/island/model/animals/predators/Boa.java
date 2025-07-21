package com.island.model.animals.predators;

import com.island.model.animals.Predator;

public class Boa extends Predator {
    public Boa() {
        super(15, 30, 1, 3);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
