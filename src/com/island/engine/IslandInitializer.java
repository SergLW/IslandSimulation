package com.island.engine;

import com.island.map.Island;
import com.island.map.Location;
import com.island.model.GameObject;
import com.island.model.animals.Animal;
import com.island.model.plants.Plant;
import com.island.util.GameObjectFactoryUtil;
import com.island.util.ObjectsParameters;
import com.island.util.RandomUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class IslandInitializer {

    public static void  fillIsland(Island island) {
        System.out.println("Starting island initialization...");
        createObjects(island, ObjectsParameters.getAllRegisteredConfigs());
        System.out.println("...Island initialized");
    }

    private static void createObjects(Island island, Set<Class<? extends GameObject>> objectTypes) {
            // For visualization
        List<Location> allLocations = island.getAllLocations();
            // ---
        int total = allLocations.size();
        int current = 0;
        int printProgress = -1;
            //----
        for (Location location : island.getAllLocations()) {
            //--- For statistic
            Map<String, Integer> localStats = new LinkedHashMap<>();
            //---
            for (Class<? extends GameObject> objectType : objectTypes) {
                GameObject newObject = GameObjectFactoryUtil.createGameObject(objectType);

                int count;
                if (newObject instanceof Animal animal) {
                    count = ThreadLocalRandom.current().nextInt(0, (int) animal.getMaxCount()/3 + 1);
                    if (count == 0) break;
                    for (int i = 0; i < count; i++) {
                        Animal newAnimal = GameObjectFactoryUtil.createGameObject(objectType.asSubclass(Animal.class));
                        newAnimal.setAge(ThreadLocalRandom.current().nextInt(newAnimal.getMaxAge()/4, newAnimal.getMaxAge()/2));
                        newAnimal.setCurrentHunger(RandomUtil.getRandomInitialCurrentHunger(newAnimal));
                        newAnimal.setMaxTimeNotEat(ThreadLocalRandom.current().nextInt(3, 6));
                        location.addAnimal(newAnimal);
                    }
                    //--Statistic
                    localStats.merge(animal.getClass().getSimpleName(), count, Integer::sum);
                } else if (newObject instanceof Plant plant) {
                    count = ThreadLocalRandom.current().nextInt(100, plant.getMaxCount() + 1);
                    for (int i = 0; i < count; i++) {
                        Plant newPlant = GameObjectFactoryUtil.createGameObject(objectType.asSubclass(Plant.class));
                        newPlant.setAge(ThreadLocalRandom.current().nextInt(0, 2));
                        location.growPlant(newPlant);
                    }
                    //--Statistic
                    localStats.merge(plant.getClass().getSimpleName(), count, Integer::sum);
                }
            }

            // Log visualization
            boolean longInit = false;
            if (longInit) {
                String printLocation = localStats.entrySet().stream()
                        .map(e -> e.getKey() + ": " + e.getValue())
                        .collect(Collectors.joining(", "));
                System.out.printf("  [%d, %d] %s%n", location.getX(), location.getY(), printLocation);
            }
            // %
            current++;
            int progress = (current * 100) / total;
            if (progress != printProgress) {
                if (longInit) {
                    System.out.println(" Progress: " + progress + "%");
                } else {
                    System.out.print("\r Progress: " + progress + "%");
                }
                printProgress = progress;
            }
        }
    }
}
