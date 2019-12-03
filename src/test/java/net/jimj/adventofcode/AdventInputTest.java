package net.jimj.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdventInputTest {

    @Test
    public void input_must_read_ints() {
        final AdventInput input = new AdventInput("/advent_input_ints");

        input.ints()
                .forEach(Assertions::assertNotNull);
    }

    @Test
    public void input_must_read_doubles() {
        final AdventInput input = new AdventInput("/advent_input_doubles");

        input.doubles()
                .forEach(Assertions::assertNotNull);
    }

    @Test
    public void input_must_read_strings() {
        final AdventInput input = new AdventInput("/advent_input_doubles");

        input.lines()
                .forEach(Assertions::assertNotNull);
    }
}
