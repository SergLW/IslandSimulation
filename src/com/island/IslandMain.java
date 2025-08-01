package com.island;

import com.island.engine.IslandEngine;
import com.island.map.Island;
import com.island.model.animals.herbivores.Boar;
import com.island.model.animals.predators.Wolf;
import com.island.model.plants.Plant;

public class IslandMain {
    public static void main(String[] args) {
        // new IslandEngine(new Island(120, 20)).startSimulation();

        System.out.println("test load Yaml");

        Wolf wolf = new Wolf();
        Boar boar = new Boar();
        Plant plant = new Plant();
        System.out.println(wolf);
        System.out.println(boar);
        System.out.println(plant);


    }
}
