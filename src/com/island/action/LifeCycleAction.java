package com.island.action;

import com.island.model.GameObject;
import com.island.model.animals.Animal;
import com.island.model.animals.herbivores.Caterpillar;
import com.island.model.plants.Plant;
import com.island.statistics.DeathReason;
import com.island.statistics.StatisticsPrinter;

public class LifeCycleAction {

    public void agingAction(GameObject object) {
        if (object instanceof Animal animal) {
            if (animal instanceof Caterpillar caterpillar) {
                caterpillar.setAge(caterpillar.getAge() + 1);
            }
            animal.setAge(animal.getAge() + 2);
            if (animal.getAge() >= animal.getMaxAge()) {
                animal.die();
                StatisticsPrinter.logDeath(animal, DeathReason.OLD_AGE);
            }
        }
        if (object instanceof Plant plant) {
            plant.setAge(plant.getAge() + 1);
            if (plant.getAge() >= plant.getMaxAge()) {
                plant.die();
            }
        }
    }

    public void dieAction(GameObject object) {
        if (object.getLocation() != null) {
            object.getLocation().removeGameObject(object);
            object.setLocation(null);
        }
    }
}
