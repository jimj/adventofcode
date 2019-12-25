package net.jimj.adventofcode.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Grid<T> {
    private int minX, maxX;
    private int minY, maxY;

    private final Map<Point, T> grid = new HashMap<>();
    private final Supplier<T> defaultValue;

    public Grid(
            final Supplier<T> defaultValue) {
        this.defaultValue = defaultValue;
    }

    public T get(
            final Point point) {
        adjustSize(point);
        return grid.getOrDefault(point, defaultValue.get());
    }

    public void set(
            final Point point,
            final T value) {
        grid.compute(point, (p, v) -> {
            adjustSize(p);
            return value;
        });
    }

    public int getWidth() {
        return maxX - minX + 1;
    }

    public int getHeight() {
        return maxY - minY + 1;
    }

    public int getArea() {
        return getWidth() * getHeight();
    }

    public Stream<List<T>> rows() {
        final Stream.Builder<List<T>> streamBuilder = Stream.builder();

        for (int y = maxY; y >= minY; y--) {
            final List<T> values = new ArrayList<>();
            for (int x = 0; x <= maxX; x++) {
                values.add(get(new Point(x, y)));
            }
            streamBuilder.accept(values);
        }

        return streamBuilder.build();
    }

    private void adjustSize(
            final Point point) {
        minX = Math.min(minX, point.getX());
        maxX = Math.max(maxX, point.getX());
        minY = Math.min(minY, point.getY());
        maxY = Math.max(maxY, point.getY());
    }
}
