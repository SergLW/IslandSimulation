package com.island.util;

import com.island.map.Island;
import com.island.map.Location;
import com.island.model.animals.Animal;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    public static Location getRandomLocation(Island island, Location currentLocation, int speed) {
        int currentX = currentLocation.getX();
        int currentY = currentLocation.getY();
        int width = island.getWidth();
        int height = island.getHeight();

        int dx = ThreadLocalRandom.current().nextInt(-speed, speed + 1);
        int dy = ThreadLocalRandom.current().nextInt(-speed, speed + 1);

        int newX = currentX + dx;
        int newY = currentY + dy;

        if (dx == 0 && dy == 0) {
            return currentLocation;
        }

        if (newX < 0) {
            newX = -newX;
        } else if (newX >= width) {
            int overMove = newX - (width - 1);
            newX = (width - 1) - overMove;
        }

        if (newY < 0) {
            newY = -newY;
        } else if (newY >= height) {
            int overMove = newY - (height - 1);
            newY = (height - 1) - overMove;
        }

        return island.getLocation(newX, newY);
    }

    public static double getRandomInitialCurrentHunger(Animal animal) {
        double needed = animal.getFoodNeeded();
        if (needed == 0) return 0.0;
        double min = needed * 0.2;
        double max = needed * 0.7;
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

}
