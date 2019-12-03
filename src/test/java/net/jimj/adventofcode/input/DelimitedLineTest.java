package net.jimj.adventofcode.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class DelimitedLineTest {
    @Test
    public void must_support_arbitrary_delimiter() {
        final String inputLine = "foo@bar@baz@corge";

        final DelimitedLine delimitedLine = new DelimitedLine(inputLine, '@');

        final List<String> actualStrings = delimitedLine.strings().collect(Collectors.toList());

        Assertions.assertEquals(4, actualStrings.size());
    }

    @Test
    public void must_parse_strings() {
        final String inputLine = "foo,bar,baz,corge";

        final DelimitedLine delimitedLine = DelimitedLine.commaDelimited(inputLine);

        final List<String> actualStrings = delimitedLine.strings().collect(Collectors.toList());

        Assertions.assertEquals(4, actualStrings.size());
        Assertions.assertEquals("foo", actualStrings.get(0));
        Assertions.assertEquals("bar", actualStrings.get(1));
        Assertions.assertEquals("baz", actualStrings.get(2));
        Assertions.assertEquals("corge", actualStrings.get(3));
    }

    @Test
    public void must_parse_ints() {
        final String inputLine = "1,1,2,3";

        final DelimitedLine delimitedLine = DelimitedLine.commaDelimited(inputLine);

        final int[] actualInts = delimitedLine.ints().toArray();
        Assertions.assertEquals(1, actualInts[0]);
        Assertions.assertEquals(1, actualInts[1]);
        Assertions.assertEquals(2, actualInts[2]);
        Assertions.assertEquals(3, actualInts[3]);
    }
}
