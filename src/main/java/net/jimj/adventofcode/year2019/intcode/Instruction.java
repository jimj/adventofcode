package net.jimj.adventofcode.year2019.intcode;

public interface Instruction {
    int getOpCode();
    int getPointerSize();
    boolean visit(final int[] memory, final int pointer);
}
