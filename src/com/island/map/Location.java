package com.island.map;

import com.island.model.animals.Animal;
import com.island.model.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    public synchronized void addAnimal(Animal animal) {

    }

    public synchronized void growPlant() {

    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

}
