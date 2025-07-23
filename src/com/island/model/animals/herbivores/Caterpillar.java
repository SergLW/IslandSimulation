package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Caterpillar extends Herbivore {

    public Caterpillar() {
        super();
    }


    @Override
    public boolean isAlive() {
        return false;
    }
}
