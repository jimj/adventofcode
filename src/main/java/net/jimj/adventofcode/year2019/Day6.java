package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.input.AdventInput;
import net.jimj.adventofcode.input.DelimitedLine;
import net.jimj.adventofcode.util.UnweightedGraph;

import java.util.Map;

public class Day6 {
    public static void main(String[] args) {
        final UnweightedGraph<String> orbitMap = new UnweightedGraph<>();

        final AdventInput input = new AdventInput(2019, 6);

        input.lines()
                .map(line -> line.replace(')', ','))//String.split does not work with ) as a delimiter
                .map(DelimitedLine::commaDelimited)
                .map(DelimitedLine::split)
                .forEach(pair -> orbitMap.addEdge(pair[0], pair[1]));

        final Map<String, Integer> comOrbits = orbitMap.dijkstra("COM");
        final int totalOrbits = comOrbits.values().stream().mapToInt(Integer::intValue).sum();

        System.out.println("Total orbits: " + totalOrbits);

        final int transfers = orbitMap.dijkstra("YOU", "SAN") - 2;

        System.out.println("Oribtal transfers needed: " + transfers);
    }
}
