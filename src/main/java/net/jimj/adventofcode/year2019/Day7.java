package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.input.AdventInput;
import net.jimj.adventofcode.input.DelimitedLine;
import net.jimj.adventofcode.util.Permutation;
import net.jimj.adventofcode.year2019.intcode.Computer;
import net.jimj.adventofcode.year2019.intcode.Tape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String[] args) {
        final AtomicInteger highestSignal = new AtomicInteger(0);

        new Permutation<>(0, 1, 2, 3, 4).apply(phases -> {
            final Amplifier amplifier = new Amplifier();
            phases.forEach(amplifier::addPhase);
            amplifier.configureAsSeries();

            final int amplifiedSignal = amplifier.calculate();
            highestSignal.getAndUpdate(val -> Math.max(val, amplifiedSignal));
        });

        System.out.println("Highest signal (in series): " + highestSignal);

        new Permutation<>(5, 6, 7, 8, 9).apply(phases -> {
            final Amplifier amplifier = new Amplifier();
            phases.forEach(amplifier::addPhase);
            amplifier.configureAsLoop();

            final int amplifiedSignal = amplifier.calculate();
            highestSignal.getAndUpdate(val -> Math.max(val, amplifiedSignal));
        });

        System.out.println("Highest signal (looped): " + highestSignal);
    }

    private static class Amplifier {
        private final ExecutorService phaseExecutor = Executors.newFixedThreadPool(6);
        private final List<Computer> phases = new ArrayList<>();
        private Map<Computer, CompletableFuture<Void>> phaseCompletions = new HashMap<>();
        private final Map<Computer, Computer> network = new HashMap<>();

        void addPhase(
                final int phaseSetting) {
            final Computer phase = Computer.standard();
            final Tape tape = Tape.forInput(2019, 7);

            phases.add(phase);
            phaseCompletions.put(
                    phase,
                    CompletableFuture.runAsync(
                            () -> phase.compute(tape, phaseSetting),
                            phaseExecutor));
        }

        void configureAsSeries() {
            for (int i = 0 ; i < phases.size() - 1; i++) {
                network.put(phases.get(i), phases.get(i + 1));
            }
        }

        void configureAsLoop() {
            configureAsSeries();
            network.put(phases.get(phases.size() - 1), phases.get(0));
        }

        int calculate() {
            if (network.isEmpty()) {
                throw new IllegalStateException("The amplifiers must first be configured.");
            }

            final CompletableFuture<Void> phasesComplete = phaseCompletions.values().stream()
                    .reduce((f1, f2) -> CompletableFuture.allOf(f1, f2))
                    .orElseThrow(IllegalStateException::new);

            phaseExecutor.execute(() -> {
                while (!phasesComplete.isDone()) {
                    network.forEach((source, dest) -> {
                        if (!phaseCompletions.get(dest).isDone()) {
                            dest.load(source.drain());
                        }
                    });
                }

            });

            phases.get(0).input(0);

            try {
                phasesComplete.get();
                phaseExecutor.shutdownNow();
                return phases.get(phases.size() - 1).output();
            } catch (final InterruptedException  e) {
                Thread.currentThread().interrupt();
                return Integer.MIN_VALUE;
            } catch (final ExecutionException e) {
                throw new IllegalStateException("Execution failed.", e);
            }
        }
    }
}
