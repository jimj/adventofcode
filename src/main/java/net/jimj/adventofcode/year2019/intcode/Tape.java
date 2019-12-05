package net.jimj.adventofcode.year2019.intcode;

import java.util.Arrays;
import java.util.function.Consumer;

public final class Tape {
    private final int[] memory;
    private int pointer = 0;

    public Tape(
            final int[] memory) {
        this.memory = Arrays.copyOf(memory, memory.length);
    }

    public int head() {
        return memory[0];
    }

    public int read() {
        return memory[pointer];
    }

    public Consumer<Integer> writeParameter(
            final int parameterPosition) {
        return (i) -> memory[memory[pointer + parameterPosition]] = i;
    }

    public int readParameter(
            final int parameterPosition) {
        final int parameterValue = memory[pointer + parameterPosition];
        return memory[parameterValue];
    }

    public void advance(
            final int distance) {
        pointer += distance;
    }
}