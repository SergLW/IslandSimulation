package com.island.util;

import java.util.Map;

public class ObjectsParametersConfig {
    private double weight;
    private int maxCount;
    private int speed;
    private double foodNeeded;


    private int maxAge;

    private Map<String, Integer> foodProbability;

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

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public Map<String, Integer> getFoodProbability() {
        return foodProbability;
    }

    public void setFoodProbability(Map<String, Integer> foodProbability) {
        this.foodProbability = foodProbability;
    }
}
