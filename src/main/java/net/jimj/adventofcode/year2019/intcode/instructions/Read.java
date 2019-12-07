package net.jimj.adventofcode.year2019.intcode.instructions;

import net.jimj.adventofcode.year2019.intcode.InputInstruction;
import net.jimj.adventofcode.year2019.intcode.ParameterMode;
import net.jimj.adventofcode.year2019.intcode.SizedInstruction;
import net.jimj.adventofcode.year2019.intcode.Tape;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class Read implements SizedInstruction, InputInstruction {
    private CompletableFuture<Integer> input = new CompletableFuture<>();

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

        try {
            final int value = input.get();
            resultWriter.accept(value);
            input = new CompletableFuture<>();
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (final ExecutionException e) {
            throw new UncheckedIOException(new IOException(e));
        }

    }

    @Override
    public void acceptInput(int input) {
        this.input.complete(input);
    }
}
