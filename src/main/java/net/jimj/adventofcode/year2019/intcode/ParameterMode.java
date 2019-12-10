package net.jimj.adventofcode.year2019.intcode;

import java.util.Arrays;

public enum ParameterMode {
    POSITIONAL,
    IMMEDIATE,
    RELATIVE;

    public static ParameterMode forParameterCode(
            final int code) {

        return Arrays.stream(ParameterMode.values())
                .filter(mode -> mode.ordinal() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No ParameterMode " + code));
    }
}
