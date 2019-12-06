package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.input.AdventInput;
import net.jimj.adventofcode.input.DelimitedLine;
import net.jimj.adventofcode.year2019.intcode.Computer;
import net.jimj.adventofcode.year2019.intcode.Tape;
import net.jimj.adventofcode.year2019.intcode.instructions.Add;
import net.jimj.adventofcode.year2019.intcode.instructions.Halt;
import net.jimj.adventofcode.year2019.intcode.instructions.IsEqual;
import net.jimj.adventofcode.year2019.intcode.instructions.JumpIfFalse;
import net.jimj.adventofcode.year2019.intcode.instructions.JumpIfTrue;
import net.jimj.adventofcode.year2019.intcode.instructions.LessThan;
import net.jimj.adventofcode.year2019.intcode.instructions.Multiply;
import net.jimj.adventofcode.year2019.intcode.instructions.Read;
import net.jimj.adventofcode.year2019.intcode.instructions.Write;

import java.util.Arrays;

public class Day5 {
    public static void main(String[] args) {
        final Computer computer = new Computer(
                Arrays.asList(
                        new Add(),
                        new Multiply(),
                        new Read(System.in),
                        new Write(),
                        new LessThan(),
                        new IsEqual(),
                        new JumpIfTrue(),
                        new JumpIfFalse(),
                        new Halt()));

        final AdventInput input = new AdventInput(2019, 5);

        final int[] memory = input.delimitedLines()
                .flatMapToInt(DelimitedLine::ints)
                .toArray();

        final Tape tape = new Tape(memory);

        computer.compute(tape);
    }
}
