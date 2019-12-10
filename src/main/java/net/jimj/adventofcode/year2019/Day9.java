package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.year2019.intcode.Computer;
import net.jimj.adventofcode.year2019.intcode.Tape;

public class Day9 {
    public static void main(String[] args) {
        final Computer computer = Computer.standard();

        computer.compute(Tape.forInput(2019, 9), 1);
        System.out.println("Part 1: " + computer.output());

        computer.compute(Tape.forInput(2019, 9), 2);
        System.out.println("Part 2: " + computer.output());
    }
}
