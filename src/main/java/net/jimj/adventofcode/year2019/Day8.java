package net.jimj.adventofcode.year2019;

import net.jimj.adventofcode.input.AdventInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 {
    public static void main(String[] args) {
        final AdventInput input = new AdventInput(2019, 8);

        final SSIF image = input.lines()
                .map(line -> new SSIF(25, 6, line))
                .findFirst()
                .orElseThrow(IllegalStateException::new);

        final List<Integer> layerWithFewestZeroes = image.layers()
                .reduce((left, right) -> {
                    final long leftCount = left.stream().filter(i -> i == 0).count();
                    final long rightCount = right.stream().filter(i -> i == 0).count();
                    return leftCount < rightCount ? left : right;
                })
                .orElseThrow(IllegalStateException::new);

        final int onesCount = (int) layerWithFewestZeroes.stream().filter(i -> i == 1).count();
        final int twosCount = (int) layerWithFewestZeroes.stream().filter(i -> i == 2).count();

        System.out.println("Part 1: " + (onesCount * twosCount));

        image.display();
    }

    static class SSIF {
        private int width;
        private int height;
        private String data;

        private SSIF(final int width, final int height, final String input) {
            this.width = width;
            this.height = height;

            final int area = width * height;

            if (input.length() % area != 0) {
                throw new IllegalArgumentException("Input data is corrupt.");
            }

            this.data = input;
        }

        Stream<List<Integer>> layers() {
            final Stream.Builder<List<Integer>> streamBuilder = Stream.builder();

            for (int i = 0; i < (data.length() - layerSize() + 1); i+= layerSize()) {
                final String subString = data.substring(i, i + layerSize());
                final List<Integer> subList = Arrays.stream(subString.split(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                streamBuilder.accept(subList);
            }

            return streamBuilder.build();
        }

        int layerSize() {
            return width * height;
        }

        List<Integer> render() {
            final List<Integer> toRender = new ArrayList<>();

            for (int position = 0; position < layerSize(); position++) {
                final int pixelPosition = position;
                final int pixelValue = layers()
                        .map(layer -> layer.get(pixelPosition))
                        .filter(val -> val != 2)
                        .findFirst()
                        .orElse(0);
                toRender.add(pixelValue);
            }

            return toRender;
        }

        void display() {
            final List<Integer> render = render();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    final int pixelLocation = (y * width) + x;
                    final int pixel = render.get(pixelLocation);
                    System.out.print(pixel == 1 ? "█" : " ");
                }
                System.out.println("");
            }
        }
    }
}
