package Round;

import Board.Point;
import Player.Player;
import Ship.Ship;
import Utils.Display;

public class Round {
    private final Display display = new Display();

    public boolean playRound(Player opponent, Point point) {

        opponent.getBoard().getOcean()[point.getX()][point.getY()].updateSquareStatus();
        opponent.getShips().forEach(Ship::updateShipStatus);
        display.boardWithoutShips(opponent.getBoard().getOcean());
        display.printSquareStatus(opponent.getBoard().getOcean()[point.getX()][point.getY()]);

        return opponent.isAlive();
    }

}

