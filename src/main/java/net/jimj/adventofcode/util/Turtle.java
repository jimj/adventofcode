package net.jimj.adventofcode.util;

public class Turtle {
    private final String BLANK = " ";
    private final String FILLED = "█";
    private final Grid<String> grid = new Grid<>(() -> BLANK);

    private Direction direction = Direction.NORTH;
    private Point position = new Point(0, 0);

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(
            final Direction direction) {
        this.direction = direction;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(
            final Point position) {
        this.position = position;
    }

    public void advance(
            final int steps) {
        for (int i=0; i<steps;i++) {
            position = position.plus(Point.offsetForDirection(direction));
        }
    }

    public void turnLeft() {
        switch (direction) {
            case NORTH:
                direction = Direction.WEST;
                break;
            case EAST:
                direction = Direction.NORTH;
                break;
            case SOUTH:
                direction = Direction.EAST;
                break;
            case WEST:
                direction = Direction.SOUTH;
                break;
        }
    }

    public void turnRight() {
        switch (direction) {
            case NORTH:
                direction = Direction.EAST;
                break;
            case EAST:
                direction = Direction.SOUTH;
                break;
            case SOUTH:
                direction = Direction.WEST;
                break;
            case WEST:
                direction = Direction.NORTH;
                break;
        }
    }

    public void fill() {
        grid.set(position, FILLED);
    }

    public void blank() {
        grid.set(position, BLANK);
    }

    public boolean isFilled() {
        return grid.get(position).equals(FILLED);
    }

    public void display() {
        grid.rows()
                .forEach(row -> {
                    row.forEach(System.out::print);
                    System.out.println();
                });
    }
}
