package net.jimj.adventofcode.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Permutation<T> {
    private final List<T> items = new ArrayList<>();

    public static void main(String[] args) {
        final Permutation<Integer> permutation = new Permutation<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        permutation.apply(System.out::println);
    }

    public Permutation(
            final List<T> collection) {
        items.addAll(collection);
    }

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
