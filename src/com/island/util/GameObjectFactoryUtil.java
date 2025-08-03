package com.island.util;

import com.island.model.GameObject;

public class GameObjectFactoryUtil {

    public static <T extends GameObject> T createGameObject(Class<T> clazz) {
        try {
            if (!GameObject.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException("Class is not GameObject");
            }
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create GameObject" + clazz.getSimpleName(), e);
        }
    }
}
