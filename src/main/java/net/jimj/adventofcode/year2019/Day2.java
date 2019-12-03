package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.input.AdventInput;
import net.jimj.adventofcode.input.DelimitedLine;
import net.jimj.adventofcode.year2019.intcode.Computer;

public class Day2 {
    public static void main(String[] args) {
        System.out.println("Part 1: " + initializeComputer(12, 2).compute());

        for (int x = 1 ; x < 100 ; x++) {
            for (int y = 1 ; y < 100 ; y++) {
                final int result = initializeComputer(x, y).compute();
                if (result == 19690720) {
                    final int answer = 100 * x + y;
                    System.out.println("Part 2: " + answer);
                }
            }
        }
    }

    private static Computer initializeComputer(
            final int noun,
            final int verb) {

        final AdventInput input = new AdventInput(2019, 2);
        final int[] initialTape = input
                .delimitedLines()
                .flatMapToInt(DelimitedLine::ints)
                .toArray();

        initialTape[1] = noun;
        initialTape[2] = verb;

        return Computer.defaultComputer(initialTape);
    }
}
