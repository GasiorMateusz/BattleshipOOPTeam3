package Round;

import Board.Point;
import Player.Player;
import Ship.Ship;
import Utils.Display;
import Utils.Input;

public class HumanRound implements Round {
    private final Display display = new Display();
    private final Input input = new Input();

    @Override
    public boolean playRound(Player current, Player opponent) {
        Point point = askForCoordinates(current, opponent);

        opponent.getBoard().getOcean()[point.getX()][point.getY()].updateSquareStatus();
        opponent.getShips().forEach(Ship::updateShipStatus);
        display.printSquareStatus(opponent.getBoard().getOcean()[point.getX()][point.getY()]);

        return opponent.isAlive();
    }

    private Point askForCoordinates(Player current, Player opponent) {
        display.printPlayerRound(current.getName());
        display.boardWithoutShips(opponent.getBoard().getOcean());
        display.chooseCoordinates();
        return input.getShot();
    }
}
