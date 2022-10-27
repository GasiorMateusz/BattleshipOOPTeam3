package Board;

import Ship.Ship;
import Ship.ShipType;
import Square.Square;
import Square.SquareStatus;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {

    /**
     * return random int in range < min, max >. The numbers at the ends of the range are included.
     */
    private int getRandomInt(int min, int max) {
        return (int) ((Math.random() * (max - min + 2)) + min - 1);
    }

    private Point getRandomPoint(int boardLength) {
        return new Point(getRandomInt(0, boardLength - 1), getRandomInt(0, boardLength - 1));
    }

    private Ship createShip(int[] startPoint, int[] endPoint, ShipType shipType) {
        List<Square> squares = new ArrayList<>();
        for (int partOfShip = 1; partOfShip <= shipType.getShipLength(); partOfShip++) {
            squares.add(
                    new Square(startPoint[0] + partOfShip * (endPoint[0] - startPoint[0]) / shipType.getShipLength(),
                            startPoint[1] + partOfShip * (endPoint[1] - startPoint[1]) / shipType.getShipLength(),
                            SquareStatus.Ship));
        }
        return new Ship(squares);
    }

    public Board randomPlacement() {
        Board board = new Board();
        int boardLength = board.getOcean().length;
        ShipType[] shipTypes = ShipType.getTypes();
        for (ShipType shipType : shipTypes) {
            Point startPoint;
            Point endPoint;
            do {
                startPoint = getRandomPoint(boardLength);
                Direction direction = Direction.values()[getRandomInt(0, Direction.values().length - 1)];
                endPoint = new Point(
                        startPoint.getX() + direction.getValue().getX() * shipType.getShipLength(),
                        startPoint.getY() + direction.getValue().getY() * shipType.getShipLength()
                );
            } while (!board.isPlacementOk(startPoint, endPoint, shipType));
            board.addShip(createShip(startPoint, endPoint, shipType));
        }
        return board;
    }

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
        return new Board();
    }

}
