package Board;

import Ship.Ship;
import Ship.ShipType;
import Square.Square;
import Square.SquareStatus;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public Square[][] ocean;
    List<Ship> ships = new ArrayList<Ship>();

    public Board() {
        populateTheOcean();
    }

    private void populateTheOcean() {
        ocean = new Square[10][10];
        for (int row = 0; row < ocean.length; row++) {
            for (int col = 0; col < ocean.length; col++) {
                ocean[row][col] = new Square(row, col, SquareStatus.Empty);
            }
        }
    }

    public void addShip(Ship ship){
        ships.add(ship);
        for (Square square: ship.getSquares()
             ) {
                ocean[square.getX()][square.getY()] = square;
        }
    }

    public Square getSquare(Square square) {
        return null;
    }

    public boolean isPlacementOk() {
        //sprawdza czy statki sie nie stykaja, czy nie wychodza poza plansze
        return false;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public Square[][] getOcean() {
        return ocean;
    }
}
