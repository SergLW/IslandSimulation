package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Deer extends Herbivore {

    public Deer() {
        super();
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
