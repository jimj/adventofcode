package net.jimj.adventofcode.year2019.intcode;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Tape is a chunk of memory that can be read from and written to.
 */
public final class Tape {
    private final int[] memory;
    private int pointer = 0;

    /**
     * Makes a copy of the memory supplied to protect from external modification.
     */
    public Tape(
            final int[] memory) {
        this.memory = Arrays.copyOf(memory, memory.length);
    }

    /**
     * Read the first cell of memory.
     */
    public int head() {
        return memory[0];
    }

    /**
     * Read memory located at the current pointer location.
     */
    public int read() {
        return memory[pointer];
    }

    /**
     * When a value is applied to the {@link Consumer} returned, that value is written to the memory location indicated by the parameter.
     */
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

    /**
     * Reads the memory location indicated by the parameter.
     */
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

    /**
     * Advances the instruction pointer the specified amount.
     */
    public void advance(
            final int distance) {
        pointer += distance;
    }

    /**
     * Jumps to the specified location.
     */
    public void seek(
            final int location) {
        pointer = location;
    }
}