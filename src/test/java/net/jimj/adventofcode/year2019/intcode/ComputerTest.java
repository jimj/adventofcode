package net.jimj.adventofcode.year2019.intcode;

import net.jimj.adventofcode.year2019.intcode.instructions.Halt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class ComputerTest {
    private final Computer simpleComputer = new Computer(Collections.singleton(new Halt()));

    @Test
    void determineParameterMode_must_fail_when_too_many_modes() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> simpleComputer.determineParameterModes(1000));
    }

    @Test
    void determineParameterMode_must_return_modes_for_all_parameters() {
        Assertions.assertEquals(
                3,
                simpleComputer.determineParameterModes(0).length);
    }

    @Test
    void determineParameterMode_must_return_default_parameter_mode_of_positional() {
        final ParameterMode[] actualModes = simpleComputer.determineParameterModes(0);

        assertModes(
                new ParameterMode[] {ParameterMode.POSITIONAL, ParameterMode.POSITIONAL, ParameterMode.POSITIONAL},
                actualModes);
    }

    @Test
    void determineParameterMode_must_parse_immediate() {
        final ParameterMode[] actualModes = simpleComputer.determineParameterModes(111);

        assertModes(
                new ParameterMode[] {ParameterMode.IMMEDIATE, ParameterMode.IMMEDIATE, ParameterMode.IMMEDIATE},
                actualModes);
    }

    @Test
    void determineParameterMode_must_parse_mixed() {
        final ParameterMode[] actualModes = simpleComputer.determineParameterModes(101);

        assertModes(
                new ParameterMode[] {ParameterMode.IMMEDIATE, ParameterMode.POSITIONAL, ParameterMode.IMMEDIATE},
                actualModes);
    }

    private void assertModes(
            final ParameterMode[] expectedModes,
            final ParameterMode[] actualModes) {

        Assertions.assertEquals(expectedModes.length, actualModes.length);
        for (int i = 0; i < expectedModes.length; i++) {
            Assertions.assertEquals(expectedModes[i], actualModes[i]);
        }
    }
}
