package com.island.util;

import java.util.Map;

public class ObjectsParametersConfig {
    // Class for YAML file Configs
    private double weight;
    private int maxCount;
    private int speed;
    private double foodNeeded;

    private int maxAge;
    private int maxOffspring;
    private int minOffspring;

    private Map<String, Integer> foodProbability;

    // Getters & Setters
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

    public int getMaxOffspring() {
        return maxOffspring;
    }

    public void setMaxOffspring(int maxOffspring) {
        this.maxOffspring = maxOffspring;
    }

    public int getMinOffspring() {
        return minOffspring;
    }

    public void setMinOffspring(int minOffspring) {
        this.minOffspring = minOffspring;
    }

    public Map<String, Integer> getFoodProbability() {
        return foodProbability;
    }

    public void setFoodProbability(Map<String, Integer> foodProbability) {
        this.foodProbability = foodProbability;
    }
}
