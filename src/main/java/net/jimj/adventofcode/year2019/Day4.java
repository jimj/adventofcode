package net.jimj.adventofcode.year2019;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {
    public static void main(String[] args) {
        final List<char[]> candidates = IntStream.rangeClosed(109165, 576723)
                .mapToObj(i -> String.valueOf(i).toCharArray())
                .filter(Day4::containsTwoAdjacentMatchingDigits)
                .filter(Day4::allDigitsAreAscending)
                .collect(Collectors.toList());

        System.out.println("There are " + candidates.size() + " candidate passwords.");

        final List<char[]> reducedCandidates = candidates.stream()
                .filter(Day4::matchingDigitsAreExactlyTwo)
                .collect(Collectors.toList());

        System.out.println("There are " + reducedCandidates.size() + " candidate passwords after further filtering.");
    }

    private static boolean containsTwoAdjacentMatchingDigits(
            final char[] digits) {

        for (int i = 0; i < digits.length - 1; i++) {
            if (digits[i] == digits[i + 1]) {
                return true;
            }
        }

        return false;
    }

    private static boolean allDigitsAreAscending(
            final char[] digits) {

        for (int i = 1; i < digits.length; i++) {
            if (digits[i] < digits[i - 1]) {
                return false;
            }
        }

        return true;
    }

    private static boolean matchingDigitsAreExactlyTwo(
            final char[] digits) {

        int currentTrackedDigitValue = digits[0];
        int currentGroupingCount = 1;
        for (int i = 1; i < digits.length; i++) {
            if (digits[i] == currentTrackedDigitValue) {
                currentGroupingCount++;
            } else {
                if (currentGroupingCount == 2) {
                    return true;
                }
                currentTrackedDigitValue = digits[i];
                currentGroupingCount = 1;
            }
        }

        return currentGroupingCount == 2;
    }
}
