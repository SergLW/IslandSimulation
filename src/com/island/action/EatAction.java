package com.island.action;

import com.island.model.GameObject;
import com.island.model.animals.Animal;
import com.island.model.plants.Plant;
import com.island.statistics.DeathReason;
import com.island.statistics.StatisticsPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.island.util.ObjectTypeUtil.isCaterpillar;

public class EatAction {

    public void eatObject(Animal animal) {
        boolean hasEaten = huntAll(animal);

        if (!hasEaten) {
            calculationHunger(animal);
        }

        animal.setHasFinishedTurn(true);
    }

    private boolean huntAll(Animal animal) {
        if (animal.getLocation() == null) return false;
        // Caterpillar
        if (isCaterpillar(animal)) {
            animal.setCurrentHunger(animal.getFoodNeeded());
            return false;
        }
        //All
        List<GameObject> allLocationObjects = new ArrayList<>(animal.getLocation().getAllGameObjects());
        allLocationObjects.remove(animal);

        double getFood = 0.0;
        boolean atLeastOneEaten = false;

        for (Map.Entry<Class<? extends GameObject>, Integer> entry : animal.getFoodProbability().entrySet()) {
            Class<? extends GameObject> foodType = entry.getKey();
            int chance = entry.getValue();

            List<GameObject> potentialTargets = allLocationObjects.stream()
                    .filter(g -> g.getLocation() != null)
                    .filter(foodType::isInstance)
                    .filter(g -> !(g instanceof Animal a) || !a.isMarkedForRelocation())
                    .collect(Collectors.toList());

            for (GameObject target : potentialTargets) {
                int plantOrAnimal = target instanceof Plant
                        ? chance
                        : ThreadLocalRandom.current().nextInt(100);

                if (plantOrAnimal <= chance) {
                    double foodWeight = getWeight(target);
                    getFood += foodWeight;
                    target.die();

                    atLeastOneEaten = true;

                    animal.setCurrentHunger(Math.min(animal.getCurrentHunger() + foodWeight, animal.getFoodNeeded()));
                    // --- Statistic
                    StatisticsPrinter.logEaten(target, animal);
                    StatisticsPrinter.logDeath(target, DeathReason.EATEN);
                    // ---
                    if (animal.getCurrentHunger() >= animal.getFoodNeeded()) {
                        animal.setCurrentHunger(animal.getCurrentHunger() * 0.6);
                        return true;
                    }
                }
            }
        }

        if (atLeastOneEaten) {
            animal.setTimeNotEat(0);
        }

        return atLeastOneEaten;
    }
    // Decreased satiety with each tick
    private void calculationHunger(Animal animal) {
        // Caterpillar
        if (isCaterpillar(animal)) return;
        //
        animal.setCurrentHunger(animal.getCurrentHunger() - (animal.getCurrentHunger() * 0.3));

        if (animal.getCurrentHunger() < 0.001) {
            animal.setTimeNotEat(animal.getTimeNotEat() + 1);
        }

        incrementTimeNotFoodNeedTimer(animal);

        if (animal.getTimeNotEat() >= animal.getMaxTimeNotEat()) {
            animal.die();
            // --- Statistic
            StatisticsPrinter.logDeath(animal, DeathReason.HUNGER);
        }


    }

    private double getWeight(GameObject target) {
        if (target instanceof Animal animal) {
            return animal.getWeight();
        }
        if (target instanceof Plant plant) {
            return plant.getWeight();
        }
        return 0.0;
    }
    /* A timer to count the number of times an animal has not been satiated
    and has started to starve - when the current hunger level is below 0.01 - Timer for MoveAction
    * */
    private void incrementTimeNotFoodNeedTimer(Animal animal) {
        if (animal.getCurrentHunger() < animal.getFoodNeeded() && animal.getCurrentHunger() >= 0.01) {
            animal.setTimesNotFoodNeed(animal.getTimesNotFoodNeed() + 1);
        } else if (animal.getCurrentHunger() >= animal.getFoodNeeded()) {
            animal.setTimesNotFoodNeed(0);
        }
    }

}
