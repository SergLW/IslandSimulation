package com.island.util;

import com.island.model.GameObject;
import com.island.model.animals.herbivores.Boar;
import com.island.model.animals.predators.Wolf;
import com.island.model.plants.Plant;

import java.util.HashMap;
import java.util.Map;

public class ObjectsParameters {

    private static final Map<Class<? extends GameObject>, ObjectsParametersConfig> configs = new HashMap<>();

    static {
        configs.put(Wolf.class , ObjectLoaderUtil.loadConfig("wolf.yaml"));
        configs.put(Boar.class , ObjectLoaderUtil.loadConfig("boar.yaml"));
        configs.put(Plant.class , ObjectLoaderUtil.loadConfig("plant.yaml"));
    }

    public static ObjectsParametersConfig getConfig(Class<? extends GameObject> clazz) {
        return configs.get(clazz);
    }

}
