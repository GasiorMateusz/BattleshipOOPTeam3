package Board;

import Ship.Ship;
import Ship.ShipType;
import Square.*;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {

//    public Board randomPlacement() {
//
//        return new Board();
//
//    }

    public void manualPlacement(Board board) {
        /*
        loop for int 5:
        ask for a  ship
        place the ship
        display board
         */
    }

    public Board testPlacement() {
        List<Ship> ships = new ArrayList<>();
        ShipType[] shipTypes = ShipType.getTypes();

        int row = 0;
        for (ShipType shipType : shipTypes) {
            List<Square> squares = new ArrayList<>();
            int shipTypeLength = shipType.getShipLength();

            for (int col = 0; col < shipTypeLength; col++) {
                squares.add(new Square(row, col, SquareStatus.Ship));
            }

            ships.add(new Ship(squares));
            row++;
        }
        return new Board(ships);
    }

}
