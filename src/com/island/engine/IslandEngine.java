package com.island.engine;

import com.island.action.MoveAction;
import com.island.map.Island;
import com.island.map.Location;
import com.island.model.animals.Animal;
import com.island.model.animals.Herbivore;
import com.island.model.animals.Predator;
import com.island.model.plants.Plant;
import com.island.statistics.StatisticsPrinter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class IslandEngine {
    private final Island island;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public IslandEngine(Island island) {
        this.island = island;
    }

    public void startSimulation() {
        IslandInitializer.fillIsland(island);

        executorService.scheduleAtFixedRate(this::runCycle, 0, 1, TimeUnit.SECONDS);
    }

    private void runCycle() {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (Location location : island.getAllLocations()) {
            executor.submit(
                    () -> {
                            for (Animal animal : location.getAnimals()) {
                                if (animal.getLocation() == null) continue;

                                animal.eat();
                                animal.reproduce();
                                animal.aging();
                                animal.move(island);
                                animal.resetTurn();
                            }

                            for (Plant plant : location.getPlants()) {
                                plant.reproduce();
                                plant.aging();
                            }
            });
        }
        // stop executor
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        // Moving after all Actions
        MoveAction moveAction = MoveAction.getInstance();
        moveAction.clearInvalidFromQueue();
        moveAction.allAnimalRelocation(island);

        // --- Statistic
        StatisticsPrinter.nextTick();
        StatisticsPrinter.printTickStats(island);

        // --- End Simulation

        String endReason = checkEndCondition(island);
        if (endReason != null) {
            System.out.println("Simulation completed. " + endReason);
            moveAction.clearQueue();
            // --- Statistic
            StatisticsPrinter.printTotal();
            // ---
            executorService.shutdown();
        }
    }

    private String checkEndCondition(Island island) {
        List<Animal> allAnimals = island.getAllLocations().stream()
                .flatMap(location -> location.getAnimals().stream())
                .toList();
        if (allAnimals.isEmpty()) {
            return "No animals left.";
        }

        List<Animal> predators = allAnimals.stream()
                .filter(animal -> animal instanceof Predator)
                .toList();

        List<Animal> herbivores = allAnimals.stream()
                .filter(animal -> animal instanceof Herbivore)
                .filter(animal -> !animal.getClass().getSimpleName().equals("Caterpillar"))
                .toList();

        boolean onlyPredatorLeft = !predators.isEmpty() && herbivores.isEmpty();
        if (onlyPredatorLeft) {
            return "Only predator left.";
        }

        Map<Class<?>, Long> herbivoreCounts = herbivores.stream()
                .collect(Collectors.groupingBy(Animal::getClass, Collectors.counting()));

        boolean noHerbivorePairs = herbivoreCounts.values().stream()
                .anyMatch(animal -> animal < 2) && predators.isEmpty();

        if (noHerbivorePairs) {
            return "Only single herbivore left.";
        }

        return null;
    }

}
