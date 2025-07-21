package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;


public class Duck extends Herbivore {

    public Duck() {
        super(1, 200, 4, 0.15);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
