package com.island.engine;

import com.island.map.Island;
import com.island.map.Location;
import com.island.model.animals.Animal;
import com.island.model.plants.Plant;
import com.island.statistics.StatisticsPrinter;

import java.util.List;
import java.util.concurrent.*;

public class IslandEngine {
    private final Island island;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public IslandEngine(Island island) {
        this.island = island;
    }

    public void startSimulation() {

        executorService.scheduleAtFixedRate(this::runCycle, 0, 1, TimeUnit.SECONDS);
    }

    private void runCycle() {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (Location location : island.getAllLocations()) {
            executor.submit(
                    () -> {
                        for (Animal animal : location.getAnimals()) {
                            //all animal activity
                        }

            });
        }
        StatisticsPrinter.print(island);
    }

    public static void populationInit(Island island, List<Class<? extends Animal>> animals, List<Class<? extends Plant>> plants) {

        for (Location location : island.getAllLocations()) {
            for (Class<? extends Animal> animalClass : animals) {
                int count = ThreadLocalRandom.current().nextInt() + 1;
            }
        }

    }

}
