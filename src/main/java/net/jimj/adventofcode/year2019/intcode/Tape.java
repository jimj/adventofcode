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
            final int parameterPosition,
            final ParameterMode[] parameterModes) {

        final ParameterMode parameterMode = parameterModes[parameterPosition - 1];
        switch (parameterMode) {
            case POSITIONAL:
                return (i) -> memory[memory[pointer + parameterPosition]] = i;
            case IMMEDIATE:
                return (i) -> memory[pointer + parameterPosition] = i;
            default:
                throw new IllegalStateException("Unhandled ParameterMode " + parameterMode);
        }
    }

    public int readParameter(
            final int parameterPosition,
            final ParameterMode[] parameterModes) {

        final ParameterMode parameterMode = parameterModes[parameterPosition - 1];
        switch (parameterMode) {
            case POSITIONAL:
                return memory[memory[pointer + parameterPosition]];
            case IMMEDIATE:
                return memory[pointer + parameterPosition];
            default:
                throw new IllegalStateException("Unhandled ParameterMode " + parameterMode);
        }
    }

    public void advance(
            final int distance) {
        pointer += distance;
    }
}