package com.island.model.plants;

import com.island.model.GameObject;

public class Plant implements GameObject {
    protected double weight;
    protected int maxCount;

    public Plant() {
        this.weight = 1.0;
        this.maxCount = 200;
    }

    public void reproduce() {

    };

    public double getWeight() {
        return weight;
    }

    public int getMaxCount() {
        return maxCount;
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
