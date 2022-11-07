package Player;

import Board.*;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayerEasy extends Player {

    public ComputerPlayerEasy(Board board, String name) {
        super(board, name);
    }

    @Override
    public Point getCoordinates(Player opponent) {
        int x = ThreadLocalRandom.current().nextInt(super.getBoard().getOcean().length);
        int y = ThreadLocalRandom.current().nextInt(super.getBoard().getOcean().length);
        return new Point(x, y);
    }

}
