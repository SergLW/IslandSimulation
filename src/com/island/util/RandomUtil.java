package com.island.util;

import com.island.map.Island;
import com.island.map.Location;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {
    public static Location getRandomLocation(Island island, Location location, int speed) {
        int x = ThreadLocalRandom.current().nextInt(-speed, speed + 1);
        int y = ThreadLocalRandom.current().nextInt(-speed, speed + 1);

        return island.getLocation(x, y);
    }
}
