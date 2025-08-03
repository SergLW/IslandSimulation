package com.island.action;

import com.island.map.Island;
import com.island.map.Location;
import com.island.model.animals.Animal;
import com.island.statistics.DeathReason;
import com.island.statistics.StatisticsPrinter;
import com.island.util.RandomUtil;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

import static com.island.util.ObjectTypeUtil.isCaterpillar;

public class MoveAction {

    // --- Singleton
    private static final MoveAction INSTANCE = new MoveAction();
    private static final Queue<Animal> overflowQueue = new ConcurrentLinkedDeque<>();

    public static MoveAction getInstance() {
        return INSTANCE;
    }
    public void enqueueRelocation(Animal animal) {
        if (!isCaterpillar(animal))
            overflowQueue.add(animal);
    }
    // ---

    // Relocation all out of the queue
    public void allAnimalRelocation(Island island) {
        while (!overflowQueue.isEmpty()) {
            Animal animal = overflowQueue.poll();
            if (animal == null
                    || animal.getLocation() == null
                    || animal.getCurrentHunger() < 0
                    || animal.getAge() >= animal.getMaxAge()
            ) {
                continue;
            }
            tryMove(animal, animal.getLocation(), island);
        }
    }

    public void movingCondition(Animal animal, Island island) {
        // Caterpillar
        if (isCaterpillar(animal)) return;

        if (animal.getMaxTimeNotEat() > 2 || animal.getTimesNotFoodNeed() >= 3) {
            tryMove(animal, animal.getLocation(), island);
        }
    }

    private void tryMove(Animal animal, Location fromLocation, Island island) {
        for (int i = 0; i < animal.getMaxTimeNotEat() + 1; i++) {
            // try move
            Location newLocation = RandomUtil.getRandomLocation(island, fromLocation, animal.getSpeed());

            if (newLocation == null) {
                failedMove(animal, fromLocation);
                return;
            }

            int countInNewLocation = newLocation.getCountOf(animal.getClass());
            if (countInNewLocation < animal.getMaxCount() + 1) {
                fromLocation.removeGameObject(animal);
                newLocation.addAnimal(animal);
                animal.setLocation(newLocation);
                animal.markForRelocation(false);
                // --- Statistic
                StatisticsPrinter.logMove(animal);
                return;
            }

            failedMove(animal, fromLocation);
        }
    }

    public void failedMove(Animal animal, Location location) {
        animal.setTimeNotEat(animal.getTimeNotEat() + 1);

        if (animal.getTimeNotEat() >= animal.getMaxTimeNotEat()) {
            animal.die();
            // --- Statistic
            StatisticsPrinter.logDeath(animal, DeathReason.HUNGER);
        }
    }

    public void clearQueue() {
        overflowQueue.clear();
    }

    public void clearInvalidFromQueue() {
        overflowQueue.removeIf(animal ->
                animal == null ||
                animal.getLocation() == null ||
                animal.getCurrentHunger() <= 0 ||
                animal.getAge() >= animal.getMaxAge()
                );
    }

}
