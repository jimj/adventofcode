package net.jimj.adventofcode.year2019.intcode.instructions;

import net.jimj.adventofcode.year2019.intcode.ParameterMode;
import net.jimj.adventofcode.year2019.intcode.SizedInstruction;
import net.jimj.adventofcode.year2019.intcode.Tape;

import java.util.function.Consumer;

public class LessThan implements SizedInstruction {
    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public int getOpCode() {
        return 7;
    }

    @Override
    public void accept(
            final Tape tape,
            final ParameterMode[] parameterModes) {

        final int operand1 = tape.readParameter(1, parameterModes);
        final int operand2 = tape.readParameter(2, parameterModes);
        final Consumer<Integer> resultWriter = tape.writeParameter(3, parameterModes);

        if (operand1 < operand2) {
            resultWriter.accept(1);
        } else {
            resultWriter.accept(0);
        }
    }
}
