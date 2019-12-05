package net.jimj.adventofcode.year2019.intcode;

public interface Instruction {
    int getOpCode();
    int getPointerSize();
    void accept(final Tape tape);
}
