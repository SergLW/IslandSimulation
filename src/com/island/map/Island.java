package com.island.map;

import java.util.Arrays;
import java.util.List;

public class Island {
    private final Location[][] map;

    public Island(int width, int height) {
        map = new Location[width][height];
    }

    public List<Location> getAllLocations() {
        return Arrays.stream(map).flatMap(Arrays::stream).toList();
    }

    public Location getLocation(int x, int y) {
        return map[x][y];
    }
}
