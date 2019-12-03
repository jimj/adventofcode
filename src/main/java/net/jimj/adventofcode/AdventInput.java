package net.jimj.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Advent of Code helper utility.
 *
 * Input is  assumed to be small enough to fit into memory without any concerns.
 */
public final class AdventInput {
    private final String resourceLocation;

    /**
     * Create an input utility for a given year and day.
     */
    public AdventInput(
            final int year,
            final int day) {
        this.resourceLocation = "/" + year + "/day" + day;
    }

    AdventInput(
            final String resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    /**
     * Return the input 1 line at a time.
     */
    public Stream<String> lines() {

        final List<String> lineCache;

        try (final InputStream input = this.getClass().getResourceAsStream(resourceLocation);
             final BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            lineCache = reader.lines().collect(Collectors.toList());
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }

        return  lineCache.stream();
    }

    /**
     * Return the input as a stream of integers.  Does not protect against conversion errors.
     */
    public IntStream ints() {
        return lines().mapToInt(Integer::parseInt);
    }

    /**
     * Return the input as a stream of doubles.  Does not protect against conversion errors.
     */
    public DoubleStream doubles() {
        return lines().mapToDouble(Double::parseDouble);
    }
}
