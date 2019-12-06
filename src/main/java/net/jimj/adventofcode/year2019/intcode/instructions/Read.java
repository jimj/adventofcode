package net.jimj.adventofcode.year2019.intcode.instructions;

import net.jimj.adventofcode.year2019.intcode.Instruction;
import net.jimj.adventofcode.year2019.intcode.ParameterMode;
import net.jimj.adventofcode.year2019.intcode.Tape;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;

public class Read implements Instruction {
    private final Scanner scanner;

    public Read(final InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    @Override
    public int getOpCode() {
        return 3;
    }

    @Override
    public int getPointerSize() {
        return 2;
    }

    @Override
    public void accept(
            final Tape tape,
            final ParameterMode[] parameterModes) {

        final Consumer<Integer> resultWriter = tape.writeParameter(1, parameterModes);
        final int value = scanner.nextInt();

        resultWriter.accept(value);
    }
}
