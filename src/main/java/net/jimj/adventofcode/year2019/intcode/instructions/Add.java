package net.jimj.adventofcode.year2019.intcode.instructions;

import net.jimj.adventofcode.year2019.intcode.Instruction;
import net.jimj.adventofcode.year2019.intcode.Tape;

import java.util.function.Consumer;

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
    public void accept(
            final Tape tape) {

        final int operand1 = tape.readParameter(1);
        final int operand2 = tape.readParameter(2);
        final Consumer<Integer> resultWriter = tape.writeParameter(3);

        final int result = operand1 + operand2;

        resultWriter.accept(result);
    }
}
