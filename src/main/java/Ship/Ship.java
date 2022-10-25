package Ship;

import Square.Square;
import Square.SquareStatus;
import java.util.ArrayList;
import java.util.List;


public class Ship {

    List<Square> squares = new ArrayList<Square>();
    ShipType shipType;
    boolean isAlive = true;

    public boolean isShipAlive(){
        //iterate through all fields of the ship if all were hit return false,
        //otherwise true
        return squares.stream().allMatch(square -> square.getStatus() != SquareStatus.Hit);
    }

}
