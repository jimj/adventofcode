package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.input.AdventInput;
import net.jimj.adventofcode.input.DelimitedLine;
import net.jimj.adventofcode.year2019.intcode.Computer;
import net.jimj.adventofcode.year2019.intcode.Tape;

public class Day2 {
    public static void main(String[] args) {
        final Computer computer = Computer.standard();

        final Tape part1Tape = initializeTape(12, 2);
        computer.compute(part1Tape);
        System.out.println("Part 1: " + part1Tape.head());

        for (int x = 1 ; x < 100 ; x++) {
            for (int y = 1 ; y < 100 ; y++) {
                final Tape part2Tape = initializeTape(x, y);
                computer.compute(part2Tape);
                if (part2Tape.head() == 19690720) {
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
