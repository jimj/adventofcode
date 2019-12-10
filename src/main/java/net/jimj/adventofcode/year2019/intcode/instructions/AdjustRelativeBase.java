package net.jimj.adventofcode.year2019.intcode.instructions;

import net.jimj.adventofcode.year2019.intcode.ParameterMode;
import net.jimj.adventofcode.year2019.intcode.SizedInstruction;
import net.jimj.adventofcode.year2019.intcode.Tape;

public class AdjustRelativeBase implements SizedInstruction {
    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public int getOpCode() {
        return 9;
    }

    @Override
    public void accept(
            final Tape tape,
            final ParameterMode[] parameterModes) {

        final int adjustment = (int) tape.readParameter(1, parameterModes);
        tape.adjustRelativeBase(adjustment);
    }
}
