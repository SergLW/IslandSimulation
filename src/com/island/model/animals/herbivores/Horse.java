package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;


public class Horse extends Herbivore {
    public Horse() {
        super();
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
