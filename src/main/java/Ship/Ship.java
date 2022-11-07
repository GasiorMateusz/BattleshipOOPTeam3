package Ship;

import Square.Square;
import Square.SquareStatus;

import java.util.List;

public class Ship {

    private final List<Square> squares;
    private boolean isAlive = true;

    public Ship(List<Square> squares) {
        this.squares = squares;
    }
    public void setAlive(boolean alive){
        isAlive = alive;
    }

    public boolean isShipAlive() {
        return isAlive;
    }

    public List<Square> getSquares() {
        return squares;
    }
}
