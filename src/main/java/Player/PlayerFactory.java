package Player;

import Board.Board;

public class PlayerFactory {
    public Player getPlayer(int choice, Board board, String name){
        return switch (choice) {
            case 1 -> new HumanPlayer(board, name);
            case 2 -> new ComputerPlayerEasy(board, name + " Easy");
            case 3 -> new ComputerPlayerNormal(board, name + " Normal");
            default -> null;
        };

    }
}
