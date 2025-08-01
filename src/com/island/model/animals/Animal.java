package com.island.model.animals;

import com.island.action.EatAction;
import com.island.action.LifeCycleAction;
import com.island.action.MoveAction;
import com.island.action.ReproduceAction;
import com.island.map.Island;
import com.island.map.Location;
import com.island.model.GameObject;
import com.island.util.ConverterNameUtil;
import com.island.util.ObjectsParameters;
import com.island.util.ObjectsParametersConfig;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements GameObject {
    // --- From YAML
    private final double weight;
    private final int maxCount;
    private final int speed;
    private final double foodNeeded;
    private final int maxAge;
    private final int maxOffspring;
    private final int minOffspring;
    private final Map<Class<? extends GameObject>, Integer> foodProbability;

    // --- RANDOM generated
    private int age;
    private double currentHunger;
    //--- Timers and boolean
    private int maxTimeNotEat;
    private int timeNotEat = 0;
    private int timesNotFoodNeed = 0;
    private boolean hasFinishedTurn = false;
    private boolean markedForRelocation = false;
    //-----------------
    private Location location;

    // --- Animal action Classes
    private static final EatAction eatAction = new EatAction();
    private static final MoveAction moveAction = MoveAction.getInstance();
    private static final ReproduceAction reproduceAction = new ReproduceAction(moveAction);
    private static final LifeCycleAction lifeCycleAction = new LifeCycleAction();

    // --- Constructor
    public Animal() {
        ObjectsParametersConfig config = ObjectsParameters.getConfig(this.getClass());

        this.weight = config.getWeight();
        this.maxCount = config.getMaxCount();
        this.speed = config.getSpeed();
        this.foodNeeded = config.getFoodNeeded();
        this.maxAge = config.getMaxAge();
        this.maxOffspring = config.getMaxOffspring();
        this.minOffspring = config.getMinOffspring();
        //---- foodProbability
        this.foodProbability = ConverterNameUtil.stringToClassName(config.getFoodProbability());
    }

    // ---- Action methods

    public void eat() {
        eatAction.eatObject(this);
    };

    public void reproduce() {
        reproduceAction.reproduction(this);
    };

    public void move(Island island) {
        moveAction.movingCondition(this, island);
    }

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

    public int getOffspring() {
        return ThreadLocalRandom.current().nextInt(minOffspring, maxOffspring);

    }

    public double getCurrentHunger() {
        return currentHunger;
    }

    public void setCurrentHunger(double currentHunger) {
        this.currentHunger = currentHunger;
    }

    public int getTimeNotEat() {
        return timeNotEat;
    }

    public void setTimeNotEat(int timeNotEat) {
        this.timeNotEat = timeNotEat;
    }

    public int getMaxTimeNotEat() {
        return maxTimeNotEat;
    }

    public void setMaxTimeNotEat(int maxTimeNotEat) {
        this.maxTimeNotEat = maxTimeNotEat;
    }

    public int getTimesNotFoodNeed() {
        return timesNotFoodNeed;
    }


    public void setTimesNotFoodNeed(int timesNotFoodNeed) {
        this.timesNotFoodNeed = timesNotFoodNeed;
    }

    public void setHasFinishedTurn(boolean hasFinishedTurn) {
        this.hasFinishedTurn = hasFinishedTurn;
    }

    // Boolean and Timer Methods
    public boolean isReadyToMove() {
        return !hasFinishedTurn;
    }

    public boolean isMarkedForRelocation() {
        return markedForRelocation;
    }
    public void markForRelocation(boolean mark) {
        this.markedForRelocation = mark;
    }
    public boolean isHasFinishedTurn() {
        return hasFinishedTurn;
    }

    public void resetTurn() {
        hasFinishedTurn = false;
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
