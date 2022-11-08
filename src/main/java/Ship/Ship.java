package Ship;

import Square.Square;

import java.util.List;

public class Ship {

    private final List<Square> squares;
    private boolean isAlive = true;
    private final ShipType shipType;

    public Ship(List<Square> squares, ShipType shipType) {
        this.squares = squares;
        this.shipType = shipType;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isShipAlive() {
        return isAlive;
    }

    public List<Square> getSquares() {
        return squares;
    }
}
