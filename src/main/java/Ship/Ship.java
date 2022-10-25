package Ship;

import Square.Square;
import Square.SquareStatus;
import java.util.ArrayList;
import java.util.List;


public class Ship {

    List<Square> squares;
    ShipType shipType;
    boolean isAlive = true;
    public Ship(List<Square> squares){
        this.squares = squares;
    }

    public boolean isShipAlive(){
        return isAlive;
    }

    private void updateShipStatus(){
        if(!isAlive)
            return;
        isAlive = squares.stream().allMatch(square -> square.getStatus() != SquareStatus.Hit);
    }

    public List<Square> getSquares() {
        return squares;
    }
}
