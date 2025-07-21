package com.island.statistics;

import com.island.map.Island;
import com.island.map.Location;
import com.island.model.animals.Animal;

import java.util.HashMap;

public class StatisticsPrinter {

    public static void print(Island island) {
        HashMap<String, Integer> count = new HashMap<>();

        for (Location location : island.getAllLocations()) {
            for (Animal animal : location.getAnimals()) {
                String name = animal.getClass().getSimpleName();
                count.put(name, count.getOrDefault(name, 0) + 1);
            }
        }

        System.out.println("Statistics:");
    }

}
