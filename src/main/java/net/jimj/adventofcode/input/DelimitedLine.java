package net.jimj.adventofcode.input;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A {@link DelimitedLine} splits an input along a delimiter.
 */
public final class DelimitedLine {
    private final String line;
    private final char delimiter;

    public static DelimitedLine commaDelimited(
            final String line) {
        return new DelimitedLine(line, ',');
    }

    DelimitedLine(
            final String line,
            final char delimiter) {
        this.line = line;
        this.delimiter = delimiter;
    }

    public Stream<String> strings() {
        return Arrays.stream(split());
    }

    public IntStream ints() {
        return strings().mapToInt(Integer::parseInt);
    }

    public String[] split() {
        return line.split(""+delimiter);
    }
}

