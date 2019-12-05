package net.jimj.adventofcode.year2019.intcode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Computer {
    private Map<Integer, Instruction> instructions = new HashMap<>();

    public Computer(
            final Collection<Instruction> instructions) {

        for (final Instruction instruction : instructions) {
            this.instructions.put(instruction.getOpCode(), instruction);
        }
    }

    public int compute(
            final Tape tape) {

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
