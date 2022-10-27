package Board;

public enum Direction {

    SOUTH(new Point(1,0)),
    WEST(new Point(0,-1)),
    NORTH(new Point(-1,0)),
    EAST(new Point(0,1));

    private final Point direction;

    Direction(Point direction) {
        this.direction = direction;
    }

    public Point getValue(){
        return direction;
    }

}
