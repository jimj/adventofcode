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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * An IntCode {@link Computer} executes {@link Instruction}s against a {@link Tape}.
 * It provides input/output utilities.
 */
public class Computer {
    private boolean isDebug = false;
    private Map<Integer, Instruction> instructions = new HashMap<>();
    private BlockingQueue<Integer> inputQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<Integer> outputQueue = new LinkedBlockingQueue<>();

    /**
     * A standard computer that supports all known IntCode instructions.
     * @return
     */
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

        for (final Instruction instruction : this.instructions.values()) {
            if (instruction instanceof IOInstruction) {
                ((IOInstruction) instruction).withInput(this::getNextInput);
                ((IOInstruction) instruction).withOutput(this::offerOutput);
            }
        }
    }

    /**
     * Compute the tape with optional input in the input buffer.
     */
    public void compute(
            final Tape tape,
            int... input) {

        if (isDebug) {
            tape.debug();
        }

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

            if (isDebug) {
                System.out.println("Executing instruction " + instruction.getClass().getSimpleName());
            }
            instruction.accept(tape, parameterModes);
        } while (instruction.advance(tape));
    }

    public Computer debug() {
        isDebug = true;
        return this;
    }

    /**
     * Supply input to the {@link Computer}.
     */
    public void input(
            final int input) {
        if (isDebug) {
            System.out.println("Got input " + input);
        }
        inputQueue.add(input);
    }

    /**
     * Load a batch of input into the {@link Computer}.
     */
    public void load(
            final List<Integer> batch) {
        if (isDebug) {
            System.out.println("Got batch input of size " + batch.size());
        }
        batch.forEach(this::input);
    }

    private int getNextInput() {
        if (isDebug) {
            System.out.println("Polling for input");
        }
        try {
            return inputQueue.take();
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            return Integer.MIN_VALUE;
        }
    }

    /**
     * Read an output from the {@link Computer}
     *
     * This call blocks until input is available.
     */
    public int output() {
        if (isDebug) {
            System.out.println("Polling for output");
        }
        try {
            return outputQueue.take();
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            return Integer.MIN_VALUE;
        }
    }

    /**
     * Read all available output from the {@link Computer}
     *
     * This is a non-blocking call.
     */
    public List<Integer> drain() {
        final List<Integer> result = new ArrayList<>();
        outputQueue.drainTo(result);
        if (isDebug) {
            System.out.println("Draining output into batch of size " + result.size());
        }
        return result;
    }

    private void offerOutput(final int value) {
        if (isDebug) {
            System.out.println("Output provided: " + value);
        }
        outputQueue.add(value);
    }

    /**
     * Parse a full parameter code into individual parameter codes.
     * e.g. parameterCode 101 parses to [IMMEDIATE, POSITIONAL, IMMEDIATE]
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
