package net.jimj.adventofcode.util;

import java.math.BigInteger;

public class Point {
    private final int x;
    private final int y;

    public Point(
            final int x,
            final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BigInteger calculateDistanceFromOrigin() {
        return BigInteger.valueOf(Math.abs(x))
                .add(BigInteger.valueOf(Math.abs(y)));
    }

    public Point plus(
            final Point point) {
        return new Point(x + point.getX(), y + point.getY());
    }

    public static Point offsetForDirection(
            final char direction) {

        switch (Character.toUpperCase(direction)) {
            case 'U':
            case 'N':
                return new Point(0, 1);
            case 'D':
            case 'S':
                return new Point(0, -1);
            case 'L':
            case 'W':
                return new Point(-1, 0);
            case 'R':
            case 'E':
                return new Point(1, 0);
            default:
                throw new IllegalArgumentException("Unknown direction " + direction);
        }
    }

    @Override
    public boolean equals(
            final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Point point = (Point) o;

        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
