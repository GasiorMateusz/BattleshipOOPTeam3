package Round;

import Board.Point;
import Player.Player;
import Ship.Ship;
import Square.SquareStatus;
import Utils.Display;

public class Round {
    private final Display display = new Display();

    public boolean playRound(Player opponent, Point point) {

        opponent.getBoard().getOcean()[point.getX()][point.getY()].updateSquareStatus();
        opponent.getShips().forEach(this::updateShipStatus);
        display.clearScreen();
        display.boardWithoutShips(opponent.getBoard().getOcean());
        display.printSquareStatus(opponent.getBoard().getOcean()[point.getX()][point.getY()]);
        return opponent.isAlive();
    }

    public void updateShipStatus(Ship ship) {
        if (!ship.isShipAlive())
            return;
        ship.setAlive(ship.getSquares().stream().anyMatch(square -> square.getStatus() != SquareStatus.Hit));
    }

}

