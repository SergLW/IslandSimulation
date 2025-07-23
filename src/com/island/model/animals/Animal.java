package com.island.model.animals;

import com.island.map.Island;
import com.island.map.Location;
import com.island.model.GameObject;
import com.island.util.ConverterNameUtil;
import com.island.util.ObjectsParameters;
import com.island.util.ObjectsParametersConfig;
import com.island.util.RandomUtil;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements GameObject {
    // --- From YAML
    protected double weight;
    protected int maxCount;
    protected int speed;
    protected double foodNeeded;
    protected int maxAge;
    protected Map<Class<? extends GameObject>, Integer> foodProbability;
    // --- RANDOM generated
    protected int age;
    protected double currentHunger;
    protected int maxTimeNotEat;

    //-----------------
    protected Location location;



    public Animal() {
        ObjectsParametersConfig config = ObjectsParameters.getConfig(this.getClass());

        this.weight = config.getWeight();
        this.maxCount = config.getMaxCount();
        this.speed = config.getSpeed();
        this.foodNeeded = config.getFoodNeeded();
        this.maxAge = config.getMaxAge();
        //---- foodProbability
        this.foodProbability = ConverterNameUtil.stringToClassName(config.getFoodProbability());


        //--- Random
        this.age = ThreadLocalRandom.current().nextInt(0, maxAge/2);
        this.currentHunger = ThreadLocalRandom.current().nextDouble(0, foodNeeded);
        this.maxTimeNotEat = ThreadLocalRandom.current().nextInt(2, 6);
    }


    protected boolean wasEaten(GameObject gameObject) {
        Integer chance = foodProbability.getOrDefault(gameObject.getClass(), 0);
        if (chance > 0) {
            return ThreadLocalRandom.current().nextInt(100) < chance;
        }
        return false;
    }

    public void move(Island island) {
        Location newLocation = RandomUtil.getRandomLocation(island, location, speed);


    };
    public void eat() {

    };
    public void reproduce() {

    };


    public double getWeight() {
        return weight;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public int getSpeed() {
        return speed;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public Map<Class<? extends GameObject>, Integer> getFoodProbability() {
        return foodProbability;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getCurrentHunger() {
        return currentHunger;
    }

    public void setCurrentHunger(double currentHunger) {
        this.currentHunger = currentHunger;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "\nweight=" + weight +
                ", \nmaxCount=" + maxCount +
                ", \nspeed=" + speed +
                ", \nfoodNeeded=" + foodNeeded +
                ", \nmaxAge=" + maxAge +
                ", \nfoodProbability=" + foodProbability +
                ", \nage=" + age +
                ", \ncurrentHunger=" + currentHunger +
                ", \nmaxTimeNotEat=" + maxTimeNotEat +
                '}' + "\n";
    }
}
