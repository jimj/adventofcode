package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.input.AdventInput;
import net.jimj.adventofcode.input.DelimitedLine;
import net.jimj.adventofcode.util.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {
    public static void main(String[] args) {
        final AdventInput input = new AdventInput(2019, 3);

        final List<Wire> wires = input
                .delimitedLines()
                .map(Wire::new)
                .collect(Collectors.toList());

        final Set<Point> intersections = findIntersections(wires);
        part1(intersections);
        part2(intersections, wires);
    }

    private static void part1(
            final Collection<Point> intersections) {

        final Point nearestIntersection = intersections.stream()
                .reduce(
                        new Point(Integer.MAX_VALUE, Integer.MAX_VALUE),
                        (p1, p2) -> {
                            if (p1.calculateDistanceFromOrigin().compareTo(p2.calculateDistanceFromOrigin()) < 0) {
                                return p1;
                            }
                            return p2;
                        });

        System.out.println("Nearest intersection is " + nearestIntersection.calculateDistanceFromOrigin());
    }

    private static void part2(
            final Collection<Point> intersections,
            final Collection<Wire> wires) {

        int lowestLatency = Integer.MAX_VALUE;

        for (final Point intersection : intersections) {
            int latencyForIntersection = 0;
            for (final Wire wire : wires) {
                 latencyForIntersection += wire.distanceToPoint(intersection) + 1;
            }

            if (latencyForIntersection < lowestLatency) {
                lowestLatency = latencyForIntersection;
            }
        }

        System.out.println("Smallest number of steps to meet at an intersection: " + lowestLatency);
    }

    private static Set<Point> findIntersections(
            final Collection<Wire> wires) {

        final Set<Point> processedPoints = new HashSet<>();
        final Set<Point> intersections = new HashSet<>();

        wires.stream()
                .flatMap(wire -> wire.points().distinct())
                .forEach(point -> {
                    if (processedPoints.contains(point)) {
                        intersections.add(point);
                    }
                    processedPoints.add(point);
                    });

        return intersections;
    }

    static class Wire {
        private final List<Point> points = new ArrayList<>();

        Wire(
                final DelimitedLine line) {

            final Iterable<String> streamIterable = () -> line.strings().iterator();

            Point position = new Point(0, 0);
            for (final String direction : streamIterable) {
                final Point offset = Point.offsetForDirection(direction.charAt(0));
                final int distance = Integer.parseInt(direction.substring(1));

                for (int i = 0; i < distance; i++) {
                    position = position.plus(offset);
                    points.add(position);
                }
            }
        }

        int distanceToPoint(
                final Point destination) {
            return points.indexOf(destination);
        }

        Stream<Point> points() {
            return points.stream();
        }
    }
}
