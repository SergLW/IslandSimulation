package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Buffalo extends Herbivore {

    public Buffalo() {
        super(700, 10, 3, 100);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
