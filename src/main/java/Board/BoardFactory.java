package Board;

import Ship.Ship;
import Ship.ShipType;
import Square.Square;
import Square.SquareStatus;
import java.util.ArrayList;
import java.util.List;

public class BoardFactory {

    public Board randomPlacement() {
        Board board = new Board();
        int boardLength = board.getOcean().length;
        ShipType[] shipTypes = ShipType.getTypes();
        for (ShipType shipType : shipTypes) {
            Point bow;
            Point stern;
            do {
                bow = getRandomPoint(boardLength);
                Direction direction = Direction.values()[getRandomInt(0, Direction.values().length - 1)];
                stern = new Point(
                        bow.getX() + direction.getValue().getX() * shipType.getShipLength(),
                        bow.getY() + direction.getValue().getY() * shipType.getShipLength()
                );
            } while (!board.isPlacementOk(bow, stern, shipType));
            board.addShip(createShip(bow, stern, shipType));
        }
        return board;
    }
    /**
     * return random int in range < min, max >. The numbers at the ends of the range are included.
     */
    private int getRandomInt(int min, int max) {
        return (int) ((Math.random() * (max - min + 2)) + min - 1);
    }

    private Point getRandomPoint(int boardLength) {
        return new Point(getRandomInt(0, boardLength - 1), getRandomInt(0, boardLength - 1));
    }

    private Ship createShip(Point bow, Point stern, ShipType shipType) {
        List<Square> squares = new ArrayList<>();
        for (int partOfShip = 1; partOfShip <= shipType.getShipLength(); partOfShip++) {
            squares.add(
                    new Square(bow.getX() + partOfShip * (stern.getX() - bow.getX()) / shipType.getShipLength(),
                            bow.getY() + partOfShip * (stern.getY() - bow.getY()) / shipType.getShipLength(),
                            SquareStatus.Ship));
        }
        return new Ship(squares);
    }

    public void manualPlacement(Board board) {
        /*
        loop for int 5:
        ask for a  ship
        place the ship
        display board
         */
    }

}
