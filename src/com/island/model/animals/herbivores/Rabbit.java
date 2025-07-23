package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Rabbit extends Herbivore {

    public Rabbit() {
        super();
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
