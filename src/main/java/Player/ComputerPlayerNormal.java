package Player;

import Board.*;
import Ship.Ship;
import Square.SquareStatus;

import java.util.*;
import java.util.stream.Collectors;


public class ComputerPlayerNormal extends ComputerPlayerEasy {
    protected final Set<Point> pointsToAvoid = new HashSet<>();
    protected Point firstSquareOnShipHit;
    protected Point nextToShot;
    protected Ship currentShipToSunk;
    protected Direction currentShotDirection;
    protected Direction oppositeShotDirection;
    protected List<Direction> directionList = Arrays.stream(Direction.values()).collect(Collectors.toList());

    public ComputerPlayerNormal(Board board, String name) {
        super(board, name);
        Collections.shuffle(directionList);
    }

    @Override
    public Point getCoordinates() {
        if (currentShipToSunk == null)
            return generateRandomPoint();

        if (!currentShipToSunk.isShipAlive()) {
            shipSunk();
            return generateRandomPoint();
        }

        if (currentShotDirection == null)
            generateNewDirection();

        if (nextToShot == null)
            generateNextShotPoint(firstSquareOnShipHit);
        else
            generateNextShotPoint(nextToShot);

        if (super.opponentBoard.getOcean()[nextToShot.getX()][nextToShot.getY()].getStatus() == SquareStatus.Ship) {
            if (oppositeShotDirection == null)
                determineOppositeShotDirection();
            return nextToShot;
        } else {
            Point point = new Point(nextToShot.getX(), nextToShot.getY());
            if (oppositeShotDirection != null)
                currentShotDirection = oppositeShotDirection;
            else
                currentShotDirection = null;

            nextToShot = null;
            return point;
        }
    }

    private void determineOppositeShotDirection() {
        switch (currentShotDirection) {
            case WEST -> oppositeShotDirection = Direction.EAST;
            case EAST -> oppositeShotDirection = Direction.WEST;
            case NORTH -> oppositeShotDirection = Direction.SOUTH;
            case SOUTH -> oppositeShotDirection = Direction.NORTH;
        }
    }

    private void generateNextShotPoint(Point startingPoint) {
        Point point = new Point(startingPoint.getX() + currentShotDirection.getValue().getX(),
                startingPoint.getY() + currentShotDirection.getValue().getY());
        boolean isPointInRange = false;

        do{
            if (isIndexOutOfBounds(point.getX()) || isIndexOutOfBounds(point.getY()) ||
                    opponentBoard.getOcean()[point.getX()][point.getY()].getStatus() == SquareStatus.Missed) {

                setNewDirection();

                point = new Point(firstSquareOnShipHit.getX() + currentShotDirection.getValue().getX(),
                        firstSquareOnShipHit.getY() + currentShotDirection.getValue().getY());

            }else
                isPointInRange = true;

        }while(!isPointInRange);
        nextToShot =  point;
    }
    private void setNewDirection(){
        if (oppositeShotDirection != null)
            currentShotDirection = oppositeShotDirection;
        else
            generateNewDirection();
    }

    private boolean isIndexOutOfBounds(int index) {
        return index < 0 || index >= super.opponentBoard.getOcean().length;
    }

    private void shipSunk() {
        firstSquareOnShipHit = null;
        nextToShot = null;
        addPointsToSet();
        currentShipToSunk = null;
        currentShotDirection = null;
        oppositeShotDirection = null;
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
        boolean isPointPositionNotAvaiable = true;
        Point point;

        do {
            point = super.getCoordinates();
            if (pointsToAvoid.contains(point))
                continue;
            SquareStatus status = super.opponentBoard.getOcean()[point.getX()][point.getY()].getStatus();

            if (status == SquareStatus.Empty || status == SquareStatus.Ship)
                isPointPositionNotAvaiable = false;
        } while (isPointPositionNotAvaiable);

        return point;
    }

    private void addPointsToSet() {
        currentShipToSunk.getSquares().forEach(square -> {
            for (int x = -1; x < 2; x++)
                for (int y = -1; y < 2; y++)
                    pointsToAvoid.add(new Point(square.getX() + x, square.getY() + y));
        });
    }
}