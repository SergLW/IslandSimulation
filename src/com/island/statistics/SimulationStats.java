package com.island.statistics;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class SimulationStats {

    // Total Count
    private final Map<String, Integer> totalBirths = new HashMap<>();
    private final Map<String, Integer> totalMoves = new HashMap<>();
    private final Map<String, Map<String, Integer>> totalHunting = new HashMap<>();
    private final Map<DeathReason, Map<String, Integer>> totalDeaths = new EnumMap<>(DeathReason.class);
    // For every tick
    private final Map<String, Integer> birthTickCount = new HashMap<>();
    private final Map<String, Integer> moveTickCount = new HashMap<>();
    private final Map<String, Map<String, Integer>> huntingTickCount = new HashMap<>();
    private final Map<DeathReason, Map<String, Integer>> deathOneTick = new EnumMap<>(DeathReason.class);

    private int tickCount = 0;

    public SimulationStats() {
        for (DeathReason reason : DeathReason.values()) {
            deathOneTick.put(reason, new HashMap<>());
            totalDeaths.put(reason, new HashMap<>());
        }
    }

    // Increments

    public synchronized void incrementBirth(String birth) {
        increment(birth, birthTickCount, totalBirths);
    }

    public synchronized void incrementDeath(String death, DeathReason reason) {
        increment(death, deathOneTick.get(reason), totalDeaths.get(reason));
    }

    public synchronized void incrementMove(String moving) {
        increment(moving, moveTickCount, totalMoves);
    }

    public synchronized void countHunting(String predator, String prey) {
        mergeForHunting(predator, prey, huntingTickCount);
        mergeForHunting(predator, prey, totalHunting);
    }

    //Getters

    public Map<String, Integer> getTotalBirths() {
        return totalBirths;
    }

    public Map<String, Integer> getTotalMoves() {
        return totalMoves;
    }

    public Map<String, Map<String, Integer>> getTotalHunting() {
        return totalHunting;
    }

    public Map<String, Integer> getTotalDeaths(DeathReason reason) {
        return totalDeaths.get(reason);
    }

    public Map<String, Integer> getBirthTickCount() {
        return birthTickCount;
    }

    public Map<String, Integer> getMoveTickCount() {
        return moveTickCount;
    }

    public Map<String, Map<String, Integer>> getHuntingTickCount() {
        return huntingTickCount;
    }

    public Map<String, Integer> getDeathOneTick(DeathReason reason) {
        return deathOneTick.get(reason);
    }

    // TickCount

    public void nextTick() {
        tickCount++;
    }

    public int getTickCount() {
        return tickCount;
    }

    // Reset Ticks Statistic
    public synchronized void resetTickStats() {
        birthTickCount.clear();
        moveTickCount.clear();
        huntingTickCount.clear();
        deathOneTick.values().forEach(Map::clear);
    }

    //Help Methods

    private void increment(String type, Map<String, Integer> tickMap, Map<String, Integer> totalMap) {
        tickMap.merge(type, 1, Integer::sum);
        totalMap.merge(type, 1, Integer::sum);
    }

    private void mergeForHunting(String outKey, String inKey, Map<String, Map<String, Integer>> map) {
        map.computeIfAbsent(outKey, k -> new HashMap<>())
                .merge(inKey, 1, Integer::sum);
    }

}
