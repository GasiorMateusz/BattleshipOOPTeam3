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

    public List<Ship> getShips() {
        return ships;
    }

    public Square[][] getOcean() {
        return ocean;
    }

    public boolean isPlacementOk(int[] startPoint, int[] endPoint, ShipType shipType) {

        StringBuilder row = new StringBuilder();
        for (Square[] boardRow: this.getOcean()
        ) {
            for (Square square: boardRow
            ) {
                row.append(square.display());
            }
            row.append("\n");
        }
        System.out.println(row);
        System.out.println(startPoint[0]+"|"+startPoint[1]+"\n"+endPoint[0]+"|"+endPoint[1]+"\n"+shipType.getShipLength());
        for (int shipPart = 0; shipPart < shipType.getShipLength(); shipPart++) {
            for (int[] point: new int[][]{startPoint,endPoint}
                 ) {
                for (int coor: point
                     ) {
                    if(coor<0 || coor>ocean.length-1) return false;
                }
            }
            if (ocean[startPoint[0] + shipPart * (endPoint[0]-startPoint[0])/shipType.getShipLength()]
                    [startPoint[1]  + shipPart * (endPoint[1]-startPoint[1])/shipType.getShipLength()].getStatus() == SquareStatus.Ship) return false;
        }
        return true;
    }
}
