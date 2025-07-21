package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Deer extends Herbivore {

    public Deer() {
        super(300, 2, 4, 50);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
