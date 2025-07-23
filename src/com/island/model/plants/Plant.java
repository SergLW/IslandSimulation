package com.island.model.plants;

import com.island.model.GameObject;
import com.island.util.ObjectsParameters;
import com.island.util.ObjectsParametersConfig;

public class Plant implements GameObject {
    protected double weight;
    protected int maxCount;
    protected int maxAge;

    public Plant() {
        ObjectsParametersConfig config = ObjectsParameters.getConfig(this.getClass());
        this.weight = config.getWeight();
        this.maxCount = config.getMaxCount();
        this.maxAge = config.getMaxAge();
    }

    public void reproduce() {

    };

    public double getWeight() {
        return weight;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public int getMaxAge() {return maxAge; }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "\nweight=" + weight +
                ", \nmaxCount=" + maxCount +
                ", \nmaxAge=" + maxAge +
                '}'+ "\n";
    }
}
