package com.island.map;

import java.util.Arrays;
import java.util.List;

public class Island {
    private final Location[][] map;

    public Island(int width, int height) {
        map = new Location[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = new Location(x, y);
            }
        }
    }

    public List<Location> getAllLocations() {
        return Arrays.stream(map).flatMap(Arrays::stream).toList();
    }

    public Location getLocation(int x, int y) {
        return map[x][y];
    }

    public boolean isValidLocation(int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length;
    }

    public int getWidth() {
        return map.length;
    }

    public int getHeight() {
        return map[0].length;
    }
}
