package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.input.AdventInput;
import net.jimj.adventofcode.input.DelimitedLine;
import net.jimj.adventofcode.year2019.intcode.Computer;
import net.jimj.adventofcode.year2019.intcode.Tape;

public class Day5 {
    public static void main(String[] args) {
        System.out.println("Computing for AC: " + compute(1));
        System.out.println("Computing for Thermal: " + compute(5));
    }

    private static int compute(
            final int controllerId) {
        final AdventInput input = new AdventInput(2019, 5);
        final int[] memory = input.delimitedLines()
                .flatMapToInt(DelimitedLine::ints)
                .toArray();

        final Tape tape = new Tape(memory);
        final Computer computer = Computer.standard();
        computer.compute(tape, controllerId);

        int diagnosticCode = 0;
        while (diagnosticCode == 0) {
            diagnosticCode = computer.output();
        }

        return diagnosticCode;
    }
}
