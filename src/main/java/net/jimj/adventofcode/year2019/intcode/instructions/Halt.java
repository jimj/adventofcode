package net.jimj.adventofcode.year2019.intcode.instructions;

import net.jimj.adventofcode.year2019.intcode.Instruction;

public class Halt implements Instruction {
    @Override
    public int getOpCode() {
        return 99;
    }

    @Override
    public int getPointerSize() {
        return 0;
    }

    @Override
    public boolean visit(int[] memory, int pointer) {
        return false;
    }
}
