package com.island.action;

import com.island.map.Location;
import com.island.model.GameObject;
import com.island.model.animals.Animal;
import com.island.model.animals.herbivores.Caterpillar;
import com.island.model.plants.Plant;
import com.island.statistics.StatisticsPrinter;
import com.island.util.GameObjectFactoryUtil;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.island.util.ObjectTypeUtil.isCaterpillar;

public class ReproduceAction {

    private final MoveAction moveAction;

    public ReproduceAction() {
        this.moveAction = null;
    }

    public ReproduceAction(MoveAction moveAction) {
        this.moveAction = moveAction;
    }

    public void reproduction(GameObject object) {
        if (object instanceof Animal animal) {
            animalReproduction(animal);
        } else if (object instanceof Plant plant) {
            plantGrowth(plant);
        }
    }

    private void animalReproduction(Animal animal) {
        Location location = animal.getLocation();
        if (location == null) return;

        addIfEmpty(location);
        if (isCaterpillar(animal)) {
            reproduceCaterpillar(animal, location);
        } else {
            reproduceWithPartner(animal, location);
        }

    }

    private void reproduceCaterpillar(Animal caterpillar, Location location) {
        int maxCount = caterpillar.getMaxCount();
        int currentCount = location.getCountOf(caterpillar.getClass());
        int offspring = caterpillar.getOffspring();
        if (currentCount >= maxCount) return;

        int newOffspring = (currentCount + offspring <= maxCount) ? offspring : maxCount - currentCount;
        if (ThreadLocalRandom.current().nextBoolean()) {
            createChild(caterpillar, location, newOffspring);
        }
        caterpillar.setHasFinishedTurn(true);
    }

    private void reproduceWithPartner(Animal animal, Location location) {
        int offspring = animal.getOffspring();
        int maxCount = animal.getMaxCount();
        int currentCount = location.getCountOf(animal.getClass());
        if (currentCount >= maxCount) return;

        List<Animal> sameAnimals = location.getAnimals().stream()
                .filter(other -> other.getClass() == animal.getClass() && other != animal)
                .collect(Collectors.toList());

        if (sameAnimals.isEmpty()) return;

        boolean readyToReproduce = animal.getCurrentHunger() / animal.getFoodNeeded() >= 0.85;
        if (!readyToReproduce) return;

        for (Animal partnerAnimal : sameAnimals) {
            boolean partnerReady = partnerAnimal.getCurrentHunger() / partnerAnimal.getFoodNeeded() >= 0.7;
            if (!partnerReady) continue;

            createChild(animal, location, offspring);

            // If the location is overflow
            int updateCount = location.getCountOf(animal.getClass());

            ifLocationIsOverflow(animal, location, maxCount, updateCount);

            animal.setHasFinishedTurn(true);
            partnerAnimal.setHasFinishedTurn(true);
            break;
        }
    }


    private void plantGrowth(Plant plant) {
        Location location = plant.getLocation();
        if (location == null) return;

        int currentCount = location.getCountOf(plant.getClass());
        int maxCount = plant.getMaxCount();
        if (currentCount >= maxCount) return;

        int offSpringCount = plant.getOffspring();
        int newOffspring = (currentCount + offSpringCount <= maxCount) ? offSpringCount : maxCount - currentCount;

        for (int i = 0; i < newOffspring; i++) {
            Plant newPlant = GameObjectFactoryUtil.createGameObject(plant.getClass());
            newPlant.setAge(1);
            location.growPlant(newPlant);
            StatisticsPrinter.logBirth(plant);
        }
    }

    private void addIfEmpty(Location location) {
        if (location.getAnimals().stream().noneMatch(a-> isCaterpillar(a))) {
            Caterpillar animal = GameObjectFactoryUtil.createGameObject(Caterpillar.class);
            animal.setAge(1);
            animal.setCurrentHunger(0);
            animal.setMaxTimeNotEat(animal.getMaxTimeNotEat());
            location.addAnimal(animal);
            StatisticsPrinter.logBirth(animal);
        }
    }

    public void growIfEmpty(Location location) {
        if (location.getPlants().isEmpty()) {
            Plant plant = new Plant();
            plant.setAge(1);
            location.growPlant(plant);
        };
    }

    // Methods for Animal Reproduction

    private void ifLocationIsOverflow(Animal animal, Location location, int maxCount, int updateCount) {
        if (updateCount > maxCount && moveAction != null) {
            int overLimit = updateCount - maxCount;

            List<Animal> movable = location.getAnimals().stream()
                    .filter(a -> a.getClass() == animal.getClass())
                    .filter(a -> !isCaterpillar(a))
                    .filter(Animal::isReadyToMove)
                    .filter(a -> !a.isMarkedForRelocation())
                    .limit(overLimit)
                    .toList();

            for (Animal animalMovable : movable) {
                animalMovable.markForRelocation(true);
                moveAction.enqueueRelocation(animalMovable); // add to queue for relocation
            }

        }
    }

    private void createChild(Animal parent, Location location,  int offspring) {

        if (offspring <= 0) return;
        double currentHunger = isCaterpillar(parent)
                ? parent.getFoodNeeded()
                : parent.getFoodNeeded() * 0.35;

        for (int i = 0; i < offspring; i++) {
            Animal child = GameObjectFactoryUtil.createGameObject(parent.getClass());

            child.setAge(1);
            child.setCurrentHunger(currentHunger);
            child.setMaxTimeNotEat(parent.getMaxTimeNotEat());

            location.addAnimal(child);
            //Statistic
            if (!isCaterpillar(child)) {
                StatisticsPrinter.logBirth(child);
            }
        }
    }

}
