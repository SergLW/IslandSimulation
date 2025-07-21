package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Sheep extends Herbivore {

    public Sheep() {
        super(70, 140, 3, 15);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
