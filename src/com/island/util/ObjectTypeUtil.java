package com.island.util;

import com.island.model.animals.Animal;
import com.island.model.animals.Herbivore;
import com.island.model.animals.Predator;
import com.island.model.animals.herbivores.Caterpillar;

public class ObjectTypeUtil {

    private ObjectTypeUtil() {
    }

    public static boolean isCaterpillar(Animal animal) {
        return animal instanceof Caterpillar;
    }

    public static boolean isHerbivores(Animal animal) {
        return animal instanceof Herbivore;
    }

    public static boolean isPredator(Animal animal) {
        return animal instanceof Predator;
    }
}
