package net.jimj.adventofcode.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UnweightedGraph<T> {
    private final Map<T, Set<T>> edges = new HashMap<>();

    /**
     * Add an edge between 2 vertices.  If a vertex has not been seen, it is created.
     */
    public void addEdge(
            final T first,
            final T second) {

        edges.computeIfAbsent(first, (v) -> new HashSet<>())
                .add(second);

        edges.computeIfAbsent(second, (v) -> new HashSet<>())
                .add(first);
    }

    /**
     * Find the shortest path between 2 vertices, using Dijkstra's algorithm.
     */
    public int dijkstra(
            final T start,
            final T end) {

        return dijkstra(start).get(end);
    }

    /**
     * Find the shortest path map for the given vertex, using Dijkstra's algorithm.
     */
    public Map<T, Integer> dijkstra(
            final T source) {

        final Map<T, Integer> distances = edges.keySet().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        (v) -> v.equals(source) ? 0 : Integer.MAX_VALUE));

        final Set<T> unvisited = new HashSet<>(edges.keySet());

        T current;
        while(!unvisited.isEmpty() && distances.values().stream().anyMatch(i -> i < Integer.MAX_VALUE)) {
            current = selectNextShortest(unvisited, distances);
            final int currentDistance = distances.get(current);
            edges.get(current)
                    .forEach(vertex -> {
                        final int tentativeDistance = currentDistance + 1;
                        distances.computeIfPresent(vertex, (v, d) -> tentativeDistance < d ? tentativeDistance : d);
                    });

            unvisited.remove(current);
        }

        return distances;
    }

    private T selectNextShortest(
            final Set<T> unvisited,
            final Map<T, Integer> distances) {

        return distances.entrySet().stream()
                .filter(e -> unvisited.contains(e.getKey()))
                .reduce((l, r) -> l.getValue() < r.getValue() ? l : r)
                .map(Map.Entry::getKey)
                .orElseThrow(IllegalStateException::new);
    }
}
