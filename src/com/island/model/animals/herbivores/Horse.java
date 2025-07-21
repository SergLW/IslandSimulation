package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;


public class Horse extends Herbivore {
    public Horse() {
        super(400, 20, 4, 60);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
