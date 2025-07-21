package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Caterpillar extends Herbivore {

    public Caterpillar() {
        super(0.01, 1000, 0, 0);
    }


    @Override
    public boolean isAlive() {
        return false;
    }
}
