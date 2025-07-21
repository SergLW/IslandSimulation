package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Boar extends Herbivore {

    public Boar() {
        super(400, 50, 2, 50);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
