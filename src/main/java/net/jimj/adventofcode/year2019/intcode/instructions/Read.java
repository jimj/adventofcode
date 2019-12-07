package net.jimj.adventofcode.year2019.intcode.instructions;

import net.jimj.adventofcode.year2019.intcode.IOInstruction;
import net.jimj.adventofcode.year2019.intcode.ParameterMode;
import net.jimj.adventofcode.year2019.intcode.SizedInstruction;
import net.jimj.adventofcode.year2019.intcode.Tape;

import java.util.function.Consumer;

public class Read extends IOInstruction implements SizedInstruction {
    @Override
    public int getOpCode() {
        return 3;
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public void accept(
            final Tape tape,
            final ParameterMode[] parameterModes) {

        final Consumer<Integer> resultWriter = tape.writeParameter(1, parameterModes);

        final int value = nextInput();
        resultWriter.accept(value);
    }
}
