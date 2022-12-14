package Player;

import Board.*;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayerEasy extends Player {
    protected Board opponentBoard;

    public ComputerPlayerEasy(Board board, String name) {
        super(board, name);
    }

    public void setUpOpponentBoard(Board opponentBoard){
        this.opponentBoard = opponentBoard;
    }

    @Override
    public Point getCoordinates() {
        int x = ThreadLocalRandom.current().nextInt(super.getBoard().getOcean().length);
        int y = ThreadLocalRandom.current().nextInt(super.getBoard().getOcean().length);
        return new Point(x, y);
    }

}
