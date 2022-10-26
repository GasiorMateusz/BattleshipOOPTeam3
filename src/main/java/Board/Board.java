package Board;

import Ship.Ship;
import Square.*;

import java.util.List;

public class Board {

    public Square[][] ocean;
    List<Ship> ships;

    public Board(List<Ship> ships) {
        this.ships = ships;
        populateTheOcean();
    }

    private void populateTheOcean() {
        ocean = new Square[10][10];
        ships.forEach(ship -> {
            for (Square square : ship.getSquares()) {
                ocean[square.getX()][square.getY()] = square;
            }
        });
        for (int row = 0; row < ocean.length; row++) {
            for (int col = 0; col < ocean.length; col++) {
                if (ocean[row][col] == null) {
                    ocean[row][col] = new Square(row,col, SquareStatus.Empty);
                }
            }
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
