package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.input.AdventInput;

public class Day1 {
    public static void main(String[] args) {
        final AdventInput input = new AdventInput(2019, 1);

        System.out.println("Part 1: " + input.ints().map(Day1::massToFuel).sum());
        System.out.println("Part 2: " + input.ints().map(Day1::totalFuel).sum());
    }

    private static int massToFuel(
            final int mass) {
        return (mass / 3 - 2);
    }

    private static int totalFuel(
            final int mass) {

        int totalFuel = 0;
        int unaccountedFuel = massToFuel(mass);

        while (unaccountedFuel > 0) {
            totalFuel += unaccountedFuel;
            unaccountedFuel = massToFuel(unaccountedFuel);
        }

        return totalFuel;
    }
}
