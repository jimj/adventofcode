package net.jimj.adventofcode.year2019.intcode.instructions;

import net.jimj.adventofcode.year2019.intcode.IOInstruction;
import net.jimj.adventofcode.year2019.intcode.ParameterMode;
import net.jimj.adventofcode.year2019.intcode.SizedInstruction;
import net.jimj.adventofcode.year2019.intcode.Tape;

public class Write extends IOInstruction implements SizedInstruction {
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

        final long value = tape.readParameter(1, parameterModes);
        sendOutput(value);
    }
}
