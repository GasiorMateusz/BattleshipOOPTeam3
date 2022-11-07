package Round;

import Board.Point;
import Player.Player;
import Ship.Ship;
import Utils.Display;

import java.util.concurrent.ThreadLocalRandom;

public class AiRound implements Round {
    private final Display display = new Display();

    @Override
    public boolean playRound(Player current, Player opponent) {
        showRoundStatus(current,opponent);
        Point point = generateRandomPoint(current);

        opponent.getBoard().getOcean()[point.getX()][point.getY()].updateSquareStatus();
        opponent.getShips().forEach(Ship::updateShipStatus);
        display.printSquareStatus(opponent.getBoard().getOcean()[point.getX()][point.getY()]);

        return opponent.isAlive();
    }

    private Point generateRandomPoint(Player current) {
        int x = ThreadLocalRandom.current().nextInt(current.getBoard().getOcean().length);
        int y = ThreadLocalRandom.current().nextInt(current.getBoard().getOcean().length);
        return new Point(x, y);
    }
    private void showRoundStatus(Player current, Player opponent){
        display.printPlayerRound(current.getName());
        display.boardWithoutShips(opponent.getBoard().getOcean());
    }
}
