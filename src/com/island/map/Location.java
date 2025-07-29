package com.island.map;

import com.island.model.GameObject;
import com.island.model.animals.Animal;
import com.island.model.plants.Plant;
import com.island.statistics.StatisticsPrinter;
import com.island.util.RemoveUtil;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private final int x;
    private final int y;

    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized List<Animal> getAnimals() {
        return animals;
    }

    public synchronized List<Plant> getPlants() {
        return plants;
    }

    public synchronized List<GameObject> getAllGameObjects() {
        List<GameObject> all = new ArrayList<>();
        all.addAll(animals);
        all.addAll(plants);
        return all;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public synchronized void addAnimal(Animal animal) {
        if (getCountOf(animal.getClass()) >= animal.getMaxCount()) return;

        animals.add(animal);
        animal.setLocation(this);
    }

    public synchronized void growPlant(Plant plant) {
        if (getCountOf(Plant.class) >= plant.getMaxCount()) return;

        plants.add(plant);
        plant.setLocation(this);
    }

    public synchronized void removeGameObject(GameObject gameObject) {
        if (gameObject == null || gameObject.getLocation() == null) return;

        if (gameObject instanceof Animal animal) {
            RemoveUtil.removeGameObjectFromList(animals, animal);
        }
        if (gameObject instanceof Plant plant) {
            RemoveUtil.removeGameObjectFromList(plants, plant);
        }
    }

    public synchronized int getCountOf(Class<? extends GameObject> objectClass) {
        int animalCount = (int) animals.stream()
                .filter(a -> a.getClass() == objectClass)
                .count();

        int plantCount = (int) plants.stream()
                .filter(p -> p.getClass() == objectClass)
                .count();

        return animalCount + plantCount;
    }


}
