package Board;

import Ship.Ship;
import Ship.ShipType;
import Square.Square;
import Square.SquareStatus;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Board {
    private final List<Ship> ships = new ArrayList<>();
    private Square[][] ocean;

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

    public void addShip(Ship ship) {
        ships.add(ship);
        for (Square square : ship.getSquares()) {
            ocean[square.getX()][square.getY()] = square;
        }
    }

    public List<Ship> getShips() {
        return ships;
    }

    public Square[][] getOcean() {
        return ocean;
    }

    public boolean canNewShipBeHere(Point squareToCheck) {
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (isInBound(new Point(squareToCheck.getX() + x, squareToCheck.getY() + y))) {
                    if (ocean[squareToCheck.getX() + x][squareToCheck.getY() + y].getStatus() == SquareStatus.Ship)
                        return false;
                }
            }
        }
        return true;
    }

    private boolean isThisSquareShipOrIsAShipNextToIt(Point bow, int shipPart,
                                                      Point shipPlacementDirection) {
        Point squareToCheck = new Point(
                bow.getX() + shipPart * shipPlacementDirection.getX(),
                bow.getY() + shipPart * shipPlacementDirection.getY());

        Point restOfTheShipDirection = new Point(
                -shipPlacementDirection.getX(),
                -shipPlacementDirection.getY());

        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (isInBound(new Point(squareToCheck.getX() + x, squareToCheck.getY() + y))) {
                    if (shipPart == 0) {
                        if (ocean[squareToCheck.getX() + x][squareToCheck.getY() + y].getStatus() == SquareStatus.Ship)
                            return true;
                    } else {
                        if (x != restOfTheShipDirection.getX() && y != restOfTheShipDirection.getY()
                        ) {
                            if (ocean[squareToCheck.getX() + x][squareToCheck.getY() + y].getStatus() == SquareStatus.Ship)
                                return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean isPlacementOk(Point bow, Point stern, ShipType shipType) {
        if (!isInBound(bow) || !isInBound(stern)) return false;
        for (int shipPart = 0; shipPart < shipType.getShipLength(); shipPart++) {
            Point direction = getShipDirection(bow, stern);
            if (isThisSquareShipOrIsAShipNextToIt(bow, shipPart, direction)) return false;
        }
        return true;
    }

    private boolean isInBound(Point point) {
        if (point.getX() < 0 || point.getX() > ocean.length - 1) return false;
        return !(point.getY() < 0 || point.getY() > ocean.length - 1);
    }

    private Point getShipDirection(Point bow, Point stern) {
        int length = abs(stern.getY() - bow.getY() + stern.getX() - bow.getX());
        return new Point(
                (stern.getX() - bow.getX()) / length,
                (stern.getY() - bow.getY()) / length
        );
    }
}
