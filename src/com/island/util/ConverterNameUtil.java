package com.island.util;

import com.island.model.GameObject;

import java.util.HashMap;
import java.util.Map;

public class ConverterNameUtil {

    public static final String[] PACKAGE_NAMES = {
            "com.island.model.animals.herbivores.",
            "com.island.model.animals.predators.",
            "com.island.model.plants."};

    public static Map<Class<? extends GameObject>, Integer> stringToClassName(Map<String, Integer> mapYaml) {
        Map<Class<? extends GameObject>, Integer> mapRestored = new HashMap<>();

        for (Map.Entry<String, Integer> entry : mapYaml.entrySet()) {
            Class<?> clazz = restoreClassPath(entry.getKey());
            mapRestored.put((Class<? extends GameObject>) clazz, entry.getValue());
        }

        return mapRestored;
    }

    private static Class<?> restoreClassPath(String className) {
        for (String packageName : PACKAGE_NAMES) {
            try {
                return Class.forName(packageName + className);
            } catch (ClassNotFoundException e) {

            }
        }
        throw new RuntimeException("Could not find class " + className);
    }
}
