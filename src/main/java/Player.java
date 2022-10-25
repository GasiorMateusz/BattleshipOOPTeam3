import Board.Board;
import Ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player {

    List<Ship> ships = new ArrayList<Ship>();
    Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isAlive() {
        //iterate through all ships and check if any of ship is alive
        return false;
    }

}
