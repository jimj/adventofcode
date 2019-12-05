package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.input.AdventInput;
import net.jimj.adventofcode.input.DelimitedLine;
import net.jimj.adventofcode.year2019.intcode.Computer;
import net.jimj.adventofcode.year2019.intcode.Tape;
import net.jimj.adventofcode.year2019.intcode.instructions.Add;
import net.jimj.adventofcode.year2019.intcode.instructions.Halt;
import net.jimj.adventofcode.year2019.intcode.instructions.Multiply;

import java.util.Arrays;

public class Day2 {
    public static void main(String[] args) {
        final Computer computer = new Computer(
                Arrays.asList(
                        new Add(),
                        new Multiply(),
                        new Halt()));

        System.out.println("Part 1: " + computer.compute(initializeTape(12, 2)));

        for (int x = 1 ; x < 100 ; x++) {
            for (int y = 1 ; y < 100 ; y++) {
                final int result = computer.compute(initializeTape(x, y));
                if (result == 19690720) {
                    final int answer = 100 * x + y;
                    System.out.println("Part 2: " + answer);
                }
            }
        }
    }

    private static Tape initializeTape(
            final int noun,
            final int verb) {

        final AdventInput input = new AdventInput(2019, 2);
        final int[] memory = input
                .delimitedLines()
                .flatMapToInt(DelimitedLine::ints)
                .toArray();

        memory[1] = noun;
        memory[2] = verb;

        return new Tape(memory);
    }
}
