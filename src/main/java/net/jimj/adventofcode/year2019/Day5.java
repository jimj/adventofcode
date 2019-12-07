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
        return Computer.STANDARD.compute(tape, controllerId)[0];
    }
}
