package Player;

import Board.*;
import Ship.Ship;
import Square.SquareStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ComputerPlayerNormal extends ComputerPlayerEasy {
    Point firstSquareOnShipHit;
    Point nextToShot;
    Ship currentShipToSunk;
    Direction currentShotDirection;
    List<Direction> directionList = Arrays.stream(Direction.values()).collect(Collectors.toList());

    public ComputerPlayerNormal(Board board, String name) {
        super(board, name);
        Collections.shuffle(directionList);
    }

    @Override
    public Point getCoordinates() {
        if (currentShipToSunk == null) {
            return generateRandomPoint();
        }

        if (!currentShipToSunk.isShipAlive()) {
            shipSunk();
            return generateRandomPoint();
        }

        if (currentShotDirection == null) {
            generateNewDirection();
        }

        if (nextToShot == null) {
            boolean isPointInRange = false;

            while (!isPointInRange) {
                nextToShot = generateNextShotPoint(firstSquareOnShipHit);

                if (isIndexOutOfBounds(nextToShot.getX()) || isIndexOutOfBounds(nextToShot.getY())) {
                    generateNewDirection();
                    continue;
                }
                isPointInRange = true;
            }
        } else {
            boolean isPointInRange = false;

            while (!isPointInRange) {
                nextToShot = generateNextShotPoint(nextToShot);

                if (isIndexOutOfBounds(nextToShot.getX()) || isIndexOutOfBounds(nextToShot.getY())) {
                    generateNewDirection();
                    continue;
                }
                isPointInRange = true;
            }
        }

        if (super.opponentBoard.getOcean()[nextToShot.getX()][nextToShot.getY()].getStatus() == SquareStatus.Ship) {
            return nextToShot;
        } else {
            Point point = new Point(nextToShot.getX(), nextToShot.getY());
            currentShotDirection = null;
            nextToShot = null;

            return point;
        }
    }

    private Point generateNextShotPoint(Point startingPoint) {
        return new Point(startingPoint.getX() + currentShotDirection.getValue().getX(),
                startingPoint.getY() + currentShotDirection.getValue().getY());
    }

    private boolean isIndexOutOfBounds(int index) {
        return index < 0 || index >= super.opponentBoard.getOcean().length;
    }

    private void shipSunk() {
        firstSquareOnShipHit = null;
        nextToShot = null;
        currentShipToSunk = null;
        currentShotDirection = null;
        directionList = Arrays.stream(Direction.values()).collect(Collectors.toList());
        Collections.shuffle(directionList);
    }

    private void generateNewDirection() {
        currentShotDirection = directionList.get(0);
        directionList.remove(0);
    }

    private Point generateRandomPoint() {

        Point toShoot = randomizePoint();

        if (super.opponentBoard.getOcean()[toShoot.getX()][toShoot.getY()].getStatus() == SquareStatus.Ship) {
            firstSquareOnShipHit = toShoot;
        }

        currentShipToSunk = super.opponentBoard.getShips()
                .stream()
                .filter(ship -> ship.getSquares().stream().anyMatch(square -> square.equals(toShoot)))
                .findAny().orElse(null);

        return toShoot;
    }

    private Point randomizePoint() {
        boolean alreadyShotPoint = true;
        Point point;

        do {
            point = super.getCoordinates();
            SquareStatus status = super.opponentBoard.getOcean()[point.getX()][point.getY()].getStatus();

            if (status == SquareStatus.Empty || status == SquareStatus.Ship)
                alreadyShotPoint = false;
        } while (alreadyShotPoint);

        return point;
    }
}
