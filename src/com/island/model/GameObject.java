package com.island.model;

import com.island.map.Island;
import com.island.map.Location;

public interface GameObject {

    Location getLocation();
    void setLocation(Location location);

    void reproduce();
    void aging();
    void die();
    default void move(Island island) {

    }
    default void eat() {

    }

    int getAge();
    int getMaxAge();
    void setAge(int age);
}
