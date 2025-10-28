package com.next.util;

public class PCG32 {
    private final long inc;
    private long state;

    public PCG32(long seed, long seq) {
        this.state = 0;
        this.inc = (seq << 1) | 1;
        nextInt();
        this.state += seed;
        nextInt();
    }

    public int nextInt() {
        long oldState = this.state;
        this.state = oldState * 6364136223846793005L + this.inc;
        int xorShifted = (int) (((oldState >>> 18) ^ oldState) >>> 27);
        int rot = (int) (oldState >>> 59);
        return Integer.rotateRight(xorShifted, rot);
    }

    public float nextFloat() {
        return (nextInt() >>> 8) * (1.0f / (1 << 24));
    }

    public boolean chance(float probability) {
        return nextFloat() < probability;
    }

    public int rollDice(int sides) {
        int bound = Integer.MAX_VALUE - (Integer.MAX_VALUE % sides);
        int r;
        do {
            r = nextInt() & 0x7FFFFFFF;
        } while (r >= bound);
        return (r % sides) + 1;
    }
}
