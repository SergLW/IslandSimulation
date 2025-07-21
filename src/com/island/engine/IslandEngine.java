package com.island.engine;

import com.island.map.Island;
import com.island.map.Location;
import com.island.model.animals.Animal;
import com.island.statistics.StatisticsPrinter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
}
