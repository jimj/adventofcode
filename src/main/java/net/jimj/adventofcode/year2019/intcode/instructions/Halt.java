package net.jimj.adventofcode.year2019.intcode.instructions;

import net.jimj.adventofcode.year2019.intcode.Instruction;
import net.jimj.adventofcode.year2019.intcode.ParameterMode;
import net.jimj.adventofcode.year2019.intcode.Tape;

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
    public void accept(Tape tape, final ParameterMode[] parameterModes) {
    }
}
