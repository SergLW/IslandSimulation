package com.island.model.plants;

import com.island.action.LifeCycleAction;
import com.island.action.ReproduceAction;
import com.island.map.Location;
import com.island.model.GameObject;
import com.island.util.ObjectsParameters;
import com.island.util.ObjectsParametersConfig;

import java.util.concurrent.ThreadLocalRandom;

public class Plant implements GameObject {
    private final double weight;
    private final int maxCount;
    private final int maxAge;
    private final int maxOffspring;
    private final int minOffspring;

    // --- RANDOM generated
    private int age;
    // ---
    private Location location;

    // --- Plants action Classes
    private static final ReproduceAction reproduceAction = new ReproduceAction();
    private static final LifeCycleAction lifeCycleAction = new LifeCycleAction();

    // Constructor
    public Plant() {
        ObjectsParametersConfig config = ObjectsParameters.getConfig(this.getClass());

        this.weight = config.getWeight();
        this.maxCount = config.getMaxCount();
        this.maxAge = config.getMaxAge();
        this.maxOffspring = config.getMaxOffspring();
        this.minOffspring = config.getMinOffspring();
    }

    // ---- Action methods
    public void reproduce() {
        reproduceAction.growIfEmpty(location);
        reproduceAction.reproduction(this);
    };

    public void aging() {
        lifeCycleAction.agingAction(this);
    }

    public void die() {
        lifeCycleAction.dieAction(this);
    }

    //--- Getters & Setters
    public double getWeight() {
        return weight;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getOffspring() {
        return ThreadLocalRandom.current().nextInt(minOffspring, maxOffspring);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // Equels & hashCode
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }
}
