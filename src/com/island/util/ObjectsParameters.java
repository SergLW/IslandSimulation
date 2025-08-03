package com.island.util;

import com.island.model.GameObject;
import com.island.model.animals.herbivores.*;
import com.island.model.animals.predators.*;
import com.island.model.plants.Plant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ObjectsParameters {
    // All parameters of animals from YAML
    private static final Map<Class<? extends GameObject>, ObjectsParametersConfig> configs = new HashMap<>();

    static {
        configs.put(Wolf.class , ObjectLoaderUtil.loadConfig("wolf.yaml"));
        configs.put(Bear.class , ObjectLoaderUtil.loadConfig("bear.yaml"));
        configs.put(Boa.class , ObjectLoaderUtil.loadConfig("boa.yaml"));
        configs.put(Fox.class , ObjectLoaderUtil.loadConfig("fox.yaml"));
        configs.put(Eagle.class , ObjectLoaderUtil.loadConfig("eagle.yaml"));

        configs.put(Horse.class , ObjectLoaderUtil.loadConfig("horse.yaml"));
        configs.put(Deer.class , ObjectLoaderUtil.loadConfig("deer.yaml"));
        configs.put(Rabbit.class , ObjectLoaderUtil.loadConfig("rabbit.yaml"));
        configs.put(Mouse.class , ObjectLoaderUtil.loadConfig("mouse.yaml"));
        configs.put(Goat.class , ObjectLoaderUtil.loadConfig("goat.yaml"));
        configs.put(Sheep.class , ObjectLoaderUtil.loadConfig("sheep.yaml"));
        configs.put(Boar.class , ObjectLoaderUtil.loadConfig("boar.yaml"));
        configs.put(Buffalo.class , ObjectLoaderUtil.loadConfig("buffalo.yaml"));
        configs.put(Duck.class , ObjectLoaderUtil.loadConfig("duck.yaml"));
        configs.put(Caterpillar.class , ObjectLoaderUtil.loadConfig("caterpillar.yaml"));

        configs.put(Plant.class , ObjectLoaderUtil.loadConfig("plant.yaml"));
    }

    public static ObjectsParametersConfig getConfig(Class<? extends GameObject> clazz) {
        return configs.get(clazz);
    }

    public static Set<Class<? extends GameObject>> getAllRegisteredConfigs() {
        return configs.keySet();
    }

}
