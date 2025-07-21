package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Mouse extends Herbivore {

    public Mouse() {
        super(0.05, 500, 1, 0.01);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
