package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.year2019.intcode.Computer;
import net.jimj.adventofcode.year2019.intcode.Tape;

public class Day5 {
    public static void main(String[] args) {
        System.out.println("Computing for AC: " + compute(1));
        System.out.println("Computing for Thermal: " + compute(5));
    }

    private static long compute(
            final int controllerId) {
        final Computer computer = Computer.standard();
        computer.compute(
                Tape.forInput(2019, 5),
                controllerId);

        long diagnosticCode = 0;
        while (diagnosticCode == 0) {
            diagnosticCode = computer.output();
        }

        return diagnosticCode;
    }
}
