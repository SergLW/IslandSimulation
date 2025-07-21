package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Rabbit extends Herbivore {

    public Rabbit() {
        super(2, 150, 2, 0.45);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
