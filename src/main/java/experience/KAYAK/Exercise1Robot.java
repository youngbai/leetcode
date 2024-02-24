package experience.KAYAK;

import java.util.Objects;

enum Direction {
    NORTH, EAST, SOUTH, WEST;
}

class Position {
    public int x, y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ',' + y + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Exercise1Robot {

    enum Instruction {
        FORWARD('F'), LEFT('L'), RIGHT('R');

        private char value;

        Instruction(char value) {
            this.value = value;
        }

        boolean equals(char c) {
            return c == value;
        }
    }

    private Position position;      // current position
    private Direction direction;    // current direction

    public Exercise1Robot(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    /**
     * Execute instructions
     *
     * @param instructions
     * @return destination position
     * @throws Exception
     */
    public Position execute(String instructions) throws Exception {

        for (char c : instructions.toCharArray()) {
            if (Instruction.FORWARD.equals(c)) {
                moveForward();
            } else if (Instruction.LEFT.equals(c)) {
                turnLeft();
            } else if (Instruction.RIGHT.equals(c)) {
                turnRight();
            } else {
                throw new Exception("illegal instruction: " + c);
            }
        }

        return position;
    }

    /**
     * Turn left
     */
    private void turnLeft() {
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

    /**
     * Turn right
     */
    private void turnRight() {
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

    /**
     * Move forward based on current positon and direction
     */
    private void moveForward() {
        switch (direction) {
            case NORTH:
                position.y++;
                break;
            case EAST:
                position.x++;
                break;
            case SOUTH:
                position.y--;
                break;
            case WEST:
                position.x--;
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        Position position = new Position(0, 0);
        Direction direction = Direction.EAST;

        Exercise1Robot robot = new Exercise1Robot(position, direction);
        Position destination = robot.execute("FF");
        System.out.println("destination is " + destination);
        System.out.println(destination.equals(new Position(2, 0)));

        destination = robot.execute("LLFFLFF");
        System.out.println("destination is " + destination);
        System.out.println(destination.equals(new Position(0, -2)));

        destination = robot.execute("RFF");
        System.out.println("destination is " + destination);
        System.out.println(destination.equals(new Position(-2, -2)));
    }

}
