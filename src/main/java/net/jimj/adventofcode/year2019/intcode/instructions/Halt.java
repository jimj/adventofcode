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
    public boolean advance(final Tape tape) {
        return false;
    }

    @Override
    public void accept(final Tape tape, final ParameterMode[] parameterModes) {
    }
}
