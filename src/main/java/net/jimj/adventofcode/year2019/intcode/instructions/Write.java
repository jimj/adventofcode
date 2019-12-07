package net.jimj.adventofcode.year2019.intcode.instructions;

import net.jimj.adventofcode.year2019.intcode.OutputInstruction;
import net.jimj.adventofcode.year2019.intcode.ParameterMode;
import net.jimj.adventofcode.year2019.intcode.SizedInstruction;
import net.jimj.adventofcode.year2019.intcode.Tape;

public class Write implements SizedInstruction, OutputInstruction {
    private int output;

    @Override
    public int getOpCode() {
        return 4;
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public void accept(
            final Tape tape,
            final ParameterMode[] parameterModes) {
        output = tape.readParameter(1, parameterModes);
    }

    @Override
    public int getOutput() {
        return output;
    }
}
