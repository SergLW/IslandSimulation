package com.island.statistics;

import com.island.map.Island;
import com.island.map.Location;
import com.island.model.GameObject;
import com.island.model.animals.Animal;
import com.island.model.plants.Plant;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticsPrinter {

    private static final SimulationStats stats = new SimulationStats();

    // logs
    public static void logBirth(GameObject object) {
        stats.incrementBirth(object.getClass().getSimpleName());
    }
    public static void logDeath(GameObject object, DeathReason reason) {
        if (object instanceof Animal)
            stats.incrementDeath(object.getClass().getSimpleName(), reason);
    }
    public static void logEaten(GameObject prey, GameObject predator) {
        if (prey == null || predator == null) return;

        stats.countHunting(
                predator.getClass().getSimpleName(),
                prey.getClass().getSimpleName()
        );
    }

    public static void logMove(GameObject object) {
        if (object.getLocation() == null) return;
        stats.incrementMove(object.getClass().getSimpleName());
    }


    public static void nextTick() {
        stats.nextTick();
    }

    // Print current Tick

    public static void printTickStats(Island island) {

        System.out.println("\n --- Tick " + stats.getTickCount() + "---");

        printCurrentObjects(island);
        printCompact("Born", stats.getBirthTickCount(), "");
        printCompact("Moves", stats.getMoveTickCount(), " times");
        printDeaths(stats::getDeathOneTick);
        printEaten(stats.getHuntingTickCount());

        stats.resetTickStats();
    }

    //Print Total Count

    public static void printTotal() {

        System.out.println("--- Total Statistics ----");
        System.out.println("Total ticks: " + stats.getTickCount());

        printCompact("Total born", stats.getTotalBirths(), "");
        printCompact("Total moves", stats.getTotalMoves(), " times");
        printDeaths(stats::getTotalDeaths);
        printEaten(stats.getTotalHunting());

    }

    // Help and internal methods

    private static void printCurrentObjects(Island island) {
        Map<String, Integer> currentAnimals = new HashMap<>();
        Map<String, Integer> currentPlants = new HashMap<>();

        for (Location location : island.getAllLocations()) {
            for (Animal animal : location.getAnimals()) {
                Class<?> clazz = animal.getClass();
                if (!Modifier.isAbstract(clazz.getModifiers())) {
                    String name = animal.getClass().getSimpleName();
                    currentAnimals.merge(name, 1, Integer::sum);
                }
            }

            int plantCount = location.getPlants().size();
            if (plantCount > 0) {
                currentPlants.merge("Plants", plantCount, Integer::sum);
            }
        }

        printCompact("Animals", currentAnimals, "");
        printCompact("Plants", currentPlants, "");
    }

    private static void printDeaths(Function<DeathReason, Map<String, Integer>> function) {
        System.out.println("Death: ");
        for (DeathReason reason : DeathReason.values()) {
            Map<String, Integer> deaths = function.apply(reason);
            if (!deaths.isEmpty()) {
                String line = formatMap(deaths, "");
                System.out.println("  " + enumNaming(reason) + ": " + line);
            } else {
                System.out.println("  " + enumNaming(reason) + ": 0");
            }
        }
    }

    private static void printEaten(Map<String, Map<String, Integer>> eaten) {
        System.out.print("Eaten: ");
        if (!eaten.isEmpty()) {
            System.out.println();
            for (var entry : eaten.entrySet()) {
                String predator = entry.getKey();
                Map<String, Integer> preyMap = entry.getValue();
                int total = preyMap.values().stream().mapToInt(Integer::intValue).sum();
                String prey = preyMap.entrySet().stream()
                        .map(e -> e.getValue() + " " + e.getKey())
                        .collect(Collectors.joining(", "));
                System.out.printf(" %d %s eaten: %s%n",total, predator, prey);
            }
        } else {
            System.out.println("No one was eaten.");
        }
    }

    private static void printCompact(String title, Map<String, Integer> countMap, String suffix) {
        if (!title.equals("Plants"))
        System.out.print(title + ": ");
        if (!countMap.isEmpty()) {
            String result = formatMap(countMap, suffix);
            System.out.println(result);
        } else {
            if (title.equals("Animals") || title.equals("Plant")) {
                System.out.println("Not found");
            } else {
                System.out.println("0");
            }
        }
    }


    private static String formatMap(Map<String, Integer> map, String suffix) {
        return map.entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue() + suffix)
                .collect(Collectors.joining(", "));
    }

    private static String enumNaming(DeathReason reason) {
        return switch (reason) {
            case HUNGER -> "From hunger";
            case OLD_AGE -> "From old age";
            case EATEN -> "Were eaten";
        };
    }

}
