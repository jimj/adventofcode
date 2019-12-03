package net.jimj.adventofcode.year2019.intcode;

import java.util.Arrays;

public class Tape {
    private final int[] memory;
    private int pointer = 0;

    Tape(
            final int[] memory) {
        this.memory = Arrays.copyOf(memory, memory.length);
    }

    int head() {
        return memory[0];
    }

    int current() {
        return memory[pointer];
    }

    boolean accept(
            final Instruction instruction) {

        final boolean shouldAdvance = instruction.visit(memory, pointer);

        if (shouldAdvance) {
            pointer += instruction.getPointerSize();
        }

        return shouldAdvance;
    }
}