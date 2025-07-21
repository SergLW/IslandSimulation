package com.island.model.animals;

import com.island.map.Island;
import com.island.map.Location;
import com.island.model.GameObject;
import com.island.util.RandomUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements GameObject {
    protected double weight;
    protected int maxCount;
    protected int speed;
    protected double foodNeeded;
    protected Location location;

    protected Map<Class<? extends GameObject>, Integer> foodProbability = Collections.emptyMap();

    public Animal(double weight, int maxCount, int speed, double foodNeeded) {
        this.weight = weight;
        this.maxCount = maxCount;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
    }



    public void move(Island island) {
        Location newLocation = RandomUtil.getRandomLocation(island, location, speed);
    };
    public void eat() {

    };
    public void reproduce() {

    };

    protected void setFoodProbability(Map<Class<? extends GameObject>, Integer> foodProbability) {
        this.foodProbability = Collections.unmodifiableMap(foodProbability);
    }

    protected boolean wasEaten(GameObject gameObject) {
        Integer chance = foodProbability.getOrDefault(gameObject.getClass(), 0);
        if (chance > 0) {
            return ThreadLocalRandom.current().nextInt(100) < chance;
        }
        return false;
    }



    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public void setFoodNeeded(double foodNeeded) {
        this.foodNeeded = foodNeeded;
    }


}
