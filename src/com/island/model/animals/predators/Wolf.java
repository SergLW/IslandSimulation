package com.island.model.animals.predators;

import com.island.model.GameObject;
import com.island.model.animals.Predator;
import com.island.model.animals.herbivores.*;

import java.util.List;
import java.util.Map;

public class Wolf extends Predator {

    public Wolf() {
        super(50, 30, 3, 8);

        setFoodProbability(Map.of(
                Horse.class, 10,
                Deer.class,	15,
                Rabbit.class, 60,
                Mouse.class, 80,
                Goat.class,	60,
                Sheep.class, 70,
                Boar.class,	15,
                Buffalo.class, 10,
                Duck.class, 40
        ));
    }

    @Override
    public boolean isAlive() {
        return false;
    }

}

/*
	Wolf	Boa	    Fox	    Bear	Eagle	Horse	Deer	Rabbit	Mouse	Goat	Sheep	Boar	Buffalo	Duck	Cat-r	Plant
Wolf	-	0	    0	    0	    0	    10	    15	    60	    80	    60	    70	    15	    10	    40	    0	    0
Boa 	0	-	    15	    0	    0	    0	    0	    20	    40	    0	    0	    0	    0	    10	    0	    0
Fox	    0	0	    -	    0	    0	    0	    0	    70	    90	    0	    0	    0	    0	    60	    40	    0
Bear	0	80	    0	    -	    0	    40	    80	    80	    90	    70	    70	    50	    20	    10	    0	    0
Eagle	0	0	    10	    0	    -	    0	    0	    90	    90	    0	    0	    0	    0	    80	    0	    0
Horse	0	0	    0	    0	    0	    -	    0	    0	    0	    0	    0	    0	    0	    0	    0	    100
Deer	0	0	    0	    0	    0	    0	    -	    0	    0	    0	    0	    0	    0	    0	    0	    100
Rabbit	0	0	    0	    0	    0	    0	    0	    -	    0	    0	    0	    0	    0	    0	    0	    100
Mouse	0	0	    0	    0	    0	    0	    0	    0	    -	    0	    0	    0	    0	    0	    90	    100
Goat	0	0	    0	    0	    0	    0	    0	    0	    0	    -	    0	    0	    0	    0	    0	    100
Sheep	0	0	    0	    0	    0	    0	    0	    0	    0	    0	    -	    0	    0	    0	    0	    100
Boar	0	0	    0	    0	    0	    0	    0	    0	    50	    0	    0	    -	    0	    0	    90	    100
Buffalo	0	0	    0	    0	    0	    0	    0	    0	    0	    0	    0	    0	    -	    0	    0	    100
Duck	0	0	    0	    0	    0	    0	    0	    0	    0	    0	    0	    0	    0	    -	    90	    100
Cat-r	0	0	    0	    0	    0	    0	    0	    0	    0	    0	    0	    0	    0	    0	    -	    100
 */
