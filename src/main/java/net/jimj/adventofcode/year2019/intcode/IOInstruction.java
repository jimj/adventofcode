package net.jimj.adventofcode.year2019.intcode;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * An {@link IOInstruction} interacts with a {@link Computer}s input and/or output.
 */
public class IOInstruction {
    private Supplier<Long> input;
    private Consumer<Long> output;

    void withInput(final Supplier<Long> input) {
        this.input = input;
    }

    void withOutput(final Consumer<Long> output) {
        this.output = output;
    }

    /**
     * Read an input from the {@link Computer}
     */
    protected long nextInput() {
        return input.get();
    }

    /**
     * Write an output to the {@link Computer}
     */
    protected void sendOutput(final long value) {
        output.accept(value);
    }
}
