package net.jimj.adventofcode.year2019.intcode;

import net.jimj.adventofcode.input.AdventInput;
import net.jimj.adventofcode.input.DelimitedLine;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Tape is a chunk of memory that can be read from and written to.
 */
public final class Tape {
    private boolean isDebug = false;
    private final int[] memory;
    private int pointer = 0;

    public static Tape forInput(
            final int year,
            final int day) {

        final int[] memory = new AdventInput(year, day)
                .delimitedLines()
                .flatMapToInt(DelimitedLine::ints)
                .toArray();

        return new Tape(memory);
    }

    /**
     * Makes a copy of the memory supplied to protect from external modification.
     */
    public Tape(
            final int[] memory) {
        this.memory = Arrays.copyOf(memory, memory.length);
    }

    void debug() {
        isDebug = true;
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
        final int parameterPointer = calculateParameterPointer(parameterPosition, parameterMode);

        return (writeVal) -> {
            if (isDebug) {
                System.out.println("\t" + parameterMode + " WRITE memory[" + parameterPointer + "] <= " + writeVal);
            }
            memory[parameterPointer] = writeVal;
        };
    }

    /**
     * Reads the memory location indicated by the parameter.
     */
    public int readParameter(
            final int parameterPosition,
            final ParameterMode[] parameterModes) {

        final ParameterMode parameterMode = parameterModes[parameterPosition - 1];
        final int parameterPointer = calculateParameterPointer(parameterPosition, parameterMode);

        final int parameterValue = memory[parameterPointer];

        if (isDebug) {
            System.out.println("\t" + parameterMode + " READ memory[" + parameterPointer + "] => " + parameterValue);
        }

        return parameterValue;
    }

    private int calculateParameterPointer(
            final int parameterPosition,
            final ParameterMode parameterMode) {
        final int pointerOffset = pointer + parameterPosition;

        switch (parameterMode) {
            case POSITIONAL:
                return memory[pointerOffset];
            case IMMEDIATE:
                return pointerOffset;
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