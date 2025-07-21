package com.island;

import com.island.engine.IslandEngine;
import com.island.map.Island;

public class IslandMain {
    public static void main(String[] args) {
        new IslandEngine(new Island(120, 20)).startSimulation();
    }
}
