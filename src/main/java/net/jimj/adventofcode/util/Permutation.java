package net.jimj.adventofcode.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * A Permutation utility to apply logic to all the permutations of a sequence.
 */
public class Permutation<T> {
    private final List<T> items = new ArrayList<>();

    public Permutation(
            final List<T> collection) {
        items.addAll(collection);
    }

    public Permutation(
            T... items) {
        this(Arrays.asList(items));
    }

    /**
     * {@link Consumer} apply is called for every permutation of the initial sequence.
     */
    public void apply(
            final Consumer<List<T>> process) {

        heapsAlgorithm(
                (T[])items.toArray(),
                items.size(),
                process);
    }

    private void heapsAlgorithm(
            final T[] seq,
            final int position,
            final Consumer<List<T>> process) {

        if (position == 1) {
            process.accept(Arrays.asList(seq));
        } else {
            final int end = position - 1;
            heapsAlgorithm(seq, end, process);

            for (int i = 0; i < end; i ++) {
                if (position % 2 == 0) {
                    swap(seq, i, end);
                } else {
                    swap(seq, 0, end);
                }
                heapsAlgorithm(seq, end, process);
            }
        }
    }

    private void swap(
            final T[] seq,
            final int a,
            final int b) {
        final T tmp = seq[a];
        seq[a] = seq[b];
        seq[b] = tmp;
    }
}
