package net.jimj.adventofcode.year2019.intcode;

import net.jimj.adventofcode.year2019.intcode.instructions.Add;
import net.jimj.adventofcode.year2019.intcode.instructions.Halt;
import net.jimj.adventofcode.year2019.intcode.instructions.Multiply;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Computer {
    private final Tape tape;
    private Map<Integer, Instruction> instructions = new HashMap<>();

    public Computer(
            final Tape tape,
            final Collection<Instruction> instructions) {
        this.tape = tape;

        for (final Instruction instruction : instructions) {
            this.instructions.put(instruction.getOpCode(), instruction);
        }
    }

    public static Computer defaultComputer(
            final int[] memory) {

        return new Computer(
                new Tape(memory),
                Arrays.asList(
                        new Add(),
                        new Multiply(),
                        new Halt())
        );
    }

    public int compute() {
        Instruction instruction;
        do {
            final int opCode = tape.current();
            instruction = instructions.get(opCode);

            if (instruction == null) {
                throw new IllegalStateException("No instruction for opCode " + opCode);
            }
        } while (tape.accept(instruction));

        return tape.head();
    }
}
