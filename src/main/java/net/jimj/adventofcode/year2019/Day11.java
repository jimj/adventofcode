package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.util.Point;
import net.jimj.adventofcode.util.Turtle;
import net.jimj.adventofcode.year2019.intcode.Computer;
import net.jimj.adventofcode.year2019.intcode.Tape;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class Day11 {
    public static void main(String[] args) {
        final Robot blackRobot = new Robot(false);
        System.out.println("Painted: " + blackRobot.paint());

        final Robot whiteRobot = new Robot(true);
        whiteRobot.paintAndDisplay();
    }

    private static class Robot {
        final Computer brain = Computer.standard();
        final Turtle turtle = new Turtle();

        Robot(final boolean startFilled) {
            if (startFilled) {
                turtle.fill();
            }
        }

        public int paint() {
            final Set<Point> paintedLocations = new HashSet<>();

            final CompletableFuture<Void> computation = CompletableFuture.runAsync(
                    () -> brain.compute(Tape.forInput(2019, 11)));

            while (!computation.isDone()) {
                if (brain.isWaitingForInput()) {
                    provideInput();
                    paintedLocations.add(processOutput());
                }
            }

            return paintedLocations.size();
        }

        public void paintAndDisplay() {
            paint();
            turtle.display();
        }

        private void provideInput() {
            if (turtle.isFilled()) {
                brain.input(1);
            } else {
                brain.input(0);
            }
        }

        private Point processOutput() {
            final long outputColor = brain.output();
            final long direction = brain.output();

            final Point paintedPosition = turtle.getPosition();
            paint(outputColor);
            move(direction);

            return paintedPosition;
        }

        private void move(
                final long direction) {
            if (direction == 0) {
                turtle.turnLeft();
            } else {
                turtle.turnRight();
            }
            turtle.advance(1);
        }

        private void paint(
                final long outputColor) {
            if (outputColor == 0) {
                turtle.blank();
            } else {
                turtle.fill();
            }
        }
    }
}
