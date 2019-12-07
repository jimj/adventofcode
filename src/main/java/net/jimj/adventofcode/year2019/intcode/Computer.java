package net.jimj.adventofcode.year2019.intcode;

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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Computer {
    private Map<Integer, Instruction> instructions = new HashMap<>();
    private BlockingQueue<Integer> inputQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<Integer> outputQueue = new LinkedBlockingQueue<>();

    public static Computer standard() {
        return new Computer(
                Arrays.asList(
                        new Add(),
                        new Multiply(),
                        new Read(),
                        new Write(),
                        new LessThan(),
                        new IsEqual(),
                        new JumpIfTrue(),
                        new JumpIfFalse(),
                        new Halt()));
    }

    public Computer(
            final Collection<Instruction> instructions) {
        for (final Instruction instruction : instructions) {
            this.instructions.put(instruction.getOpCode(), instruction);
        }
    }

    public void compute(
            final Tape tape,
            int... input) {

        Arrays.stream(input).forEach(this::input);

        Instruction instruction;
        do {
            //An instruction code is made up of an op code, which is the trailing 2 digits
            //and parameterModes, which is the remaining leading digits of the instructionCode.
            final int instructionCode = tape.read();

            final int opCode = instructionCode % 100;
            instruction = instructions.get(opCode);
            final ParameterMode[] parameterModes = determineParameterModes(instructionCode / 100);

            if (instruction == null) {
                throw new IllegalStateException("No instruction for opCode " + opCode);
            }

            if (instruction instanceof IOInstruction) {
                ((IOInstruction) instruction).withInput(this::getNextInput);
                ((IOInstruction) instruction).withOutput(outputQueue::add);
            }

            instruction.accept(tape, parameterModes);
        } while (instruction.advance(tape));
    }

    public void input(
            final int input) {
        inputQueue.add(input);
    }

    private int getNextInput() {
        try {
            return inputQueue.take();
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            return Integer.MIN_VALUE;
        }
    }

    public int output() {
        try {
            return outputQueue.take();
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            return Integer.MIN_VALUE;
        }
    }

    /**
     * A parameter code is a 0 - 3 digit number.
     */
    ParameterMode[] determineParameterModes(
            final int parameterCode) {
        if (parameterCode > 999) {
            throw new IllegalArgumentException("This computer currently supports a max of 3 parameters per instruction.");
        }

        //Positional is the default mode for a parameter.
        final ParameterMode[] result = new ParameterMode[] {
                ParameterMode.POSITIONAL,
                ParameterMode.POSITIONAL,
                ParameterMode.POSITIONAL};

        int remainingParameterCode = parameterCode;
        int parameterPosition = 0;

        while (remainingParameterCode > 0) {
            result[parameterPosition++] = ParameterMode.forParameterCode(remainingParameterCode % 10);
            remainingParameterCode /= 10;
        }

        return result;
    }
}
