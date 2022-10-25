package Ship;

import Square.Square;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    List<Square> squares = new ArrayList<Square>();
    ShipType shipType;
    boolean isAlive;

    public boolean isShipAlive(){
        //iterate through all fields of the ship if all were hit return false,
        //otherwise true
        return false;
    }

}
