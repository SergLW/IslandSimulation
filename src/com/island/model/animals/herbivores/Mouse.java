package com.island.model.animals.herbivores;

import com.island.model.animals.Herbivore;

public class Mouse extends Herbivore {

    public Mouse() {
        super();
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
