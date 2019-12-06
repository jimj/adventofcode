package net.jimj.adventofcode.year2019.intcode;

public interface SizedInstruction extends Instruction {
    int getSize();

    @Override
    default boolean advance(
            final Tape tape) {

        tape.advance(getSize());
        return true;
    }
}
