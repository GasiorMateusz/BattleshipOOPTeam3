package Player;

import Board.*;
import Utils.Display;
import Utils.Input;

public class HumanPlayer extends Player {
    private final Display display = new Display();
    private final Input input = new Input();

    public HumanPlayer(Board board, String name) {
        super(board, name);
    }

    @Override
    public Point getCoordinates() {
        display.chooseCoordinates();
        return input.getShot();
    }
}
