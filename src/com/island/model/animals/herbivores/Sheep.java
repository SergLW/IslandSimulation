package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Sheep extends Herbivore {

    public Sheep() {
        super();
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
