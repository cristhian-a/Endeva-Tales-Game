package com.next.util;

import org.junit.jupiter.api.Test;

public class PCG32Test {

    @Test
    void test() {
        int sides = 20;
        int rolls = 1000000;
        float minExpected = 0.98f;
        float maxExpected = 1.02f;
        int numberOfTests = 50;

        int perSizeChance = rolls / sides;
        float minCap = perSizeChance * minExpected;
        float maxCap = perSizeChance * maxExpected;

        System.out.println(perSizeChance);
        System.out.println("Min:" + minCap);
        System.out.println("Max:" + maxCap);
        System.out.println();

        for (int i = 0; i < numberOfTests; i++) {
            PCG32 rng = new PCG32(12345, i);
            benchmark(rng, sides, rolls, minExpected, maxExpected);
        }
    }

    private void benchmark(PCG32 rng, int sides, int rolls, float minExpected, float maxExpected) {
        int[] times = new int[sides];

        for (int i = 0; i < rolls; i++) {
            int result = rng.rollDice(sides);
            times[result-1]++;
        }

        int perSizeChance = rolls / sides;
        float minCap = perSizeChance * minExpected;
        float maxCap = perSizeChance * maxExpected;

        for (int i = 0; i < times.length; i++) {
            System.out.println("Total de vezes que caiu " + (i+1) + ": " + times[i]);
            assert times[i] > minCap && times[i] < maxCap;
        }

        System.out.println();
    }
}
