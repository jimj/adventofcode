package net.jimj.adventofcode.year2019.intcode;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * An {@link IOInstruction} interacts with a {@link Computer}s input and/or output.
 */
public class IOInstruction {
    private Supplier<Integer> input;
    private Consumer<Integer> output;

    void withInput(final Supplier<Integer> input) {
        this.input = input;
    }

    void withOutput(final Consumer<Integer> output) {
        this.output = output;
    }

    /**
     * Read an input from the {@link Computer}
     */
    protected int nextInput() {
        return input.get();
    }

    /**
     * Write an output to the {@link Computer}
     */
    protected void sendOutput(final int value) {
        output.accept(value);
    }
}
