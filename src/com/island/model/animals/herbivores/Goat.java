package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Goat extends Herbivore {

    public Goat() {
        super(60, 140, 3, 10);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
