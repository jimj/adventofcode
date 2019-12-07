package net.jimj.adventofcode.year2019.intcode;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class IOInstruction {
    private Supplier<Integer> input;
    private Consumer<Integer> output;

    void withInput(final Supplier<Integer> input) {
        this.input = input;
    }

    void withOutput(final Consumer<Integer> output) {
        this.output = output;
    }

    protected int nextInput() {
        return input.get();
    }

    protected void sendOutput(final int value) {
        output.accept(value);
    }
}
