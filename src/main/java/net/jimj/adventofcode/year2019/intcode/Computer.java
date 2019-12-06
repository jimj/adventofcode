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

    public void compute(
            final Tape tape) {

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

            instruction.accept(tape, parameterModes);
            tape.advance(instruction.getPointerSize());
        } while (instruction.getPointerSize() > 0);
    }

    /**
     * A parameter code is a 0 - 3 digit number.
     * @param parameterCode
     * @return
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
