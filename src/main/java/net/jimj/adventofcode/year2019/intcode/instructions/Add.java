package net.jimj.adventofcode.year2019.intcode.instructions;

import net.jimj.adventofcode.year2019.intcode.Instruction;

public class Add implements Instruction {
    @Override
    public int getOpCode() {
        return 1;
    }

    @Override
    public int getPointerSize() {
        return 4;
    }

    @Override
    public boolean visit(
            final int[] memory,
            final int pointer) {

        final int operand1 = memory[memory[pointer + 1]];
        final int operand2 = memory[memory[pointer + 2]];
        final int result = operand1 + operand2;

        memory[memory[pointer + 3]] = result;

        return true;
    }
}
