package net.jimj.adventofcode.year2019.intcode.instructions;

import net.jimj.adventofcode.year2019.intcode.Instruction;
import net.jimj.adventofcode.year2019.intcode.ParameterMode;
import net.jimj.adventofcode.year2019.intcode.Tape;

import java.util.function.Consumer;

public class JumpIfFalse implements Instruction {
    private Consumer<Tape> advanceHandler;

    @Override
    public int getOpCode() {
        return 6;
    }

    @Override
    public boolean advance(
            final Tape tape) {
        advanceHandler.accept(tape);
        return true;
    }

    @Override
    public void accept(
            final Tape tape,
            final ParameterMode[] parameterModes) {

        final boolean shouldJump = tape.readParameter(1, parameterModes) == 0;

        if (shouldJump) {
            final int location = tape.readParameter(2, parameterModes);
            advanceHandler = (t) -> t.seek(location);
        } else {
            advanceHandler = (t) -> t.advance(3);
        }
    }
}
