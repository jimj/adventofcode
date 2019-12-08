package net.jimj.adventofcode.year2019.intcode;

/**
 * A {@link SizedInstruction} advances the {@link Tape} a fixed amount.
 */
public interface SizedInstruction extends Instruction {
    int getSize();

    @Override
    default boolean advance(
            final Tape tape) {

        tape.advance(getSize());
        return true;
    }
}
