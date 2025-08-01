package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;


public class Duck extends Herbivore {

    public Duck() {
        super();
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
