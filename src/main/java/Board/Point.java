package Board;

import java.util.Objects;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Point) {
            Point point = (Point) o;
            return x == point.x && y == point.y;
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
