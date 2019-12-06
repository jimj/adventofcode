package net.jimj.adventofcode.year2019.intcode;

public interface Instruction {
    int getOpCode();

    /**
     * Returns false if the tape was not advanced.
     */
    boolean advance(final Tape tape);

    /**
     * Execute the instruction with the given tape & parameter modes.
     */
    void accept(final Tape tape, final ParameterMode[] parameterModes);
}
