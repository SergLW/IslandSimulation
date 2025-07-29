package com.island;

import com.island.engine.IslandEngine;
import com.island.map.Island;
import com.island.model.animals.herbivores.Boar;
import com.island.model.animals.predators.Wolf;
import com.island.model.plants.Plant;

public class IslandMain {
    public static void main(String[] args) {
        new IslandEngine(new Island(120, 20)).startSimulation();
    }
}
