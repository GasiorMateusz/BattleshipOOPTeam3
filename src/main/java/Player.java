import Board.Board;
import Board.BoardFactory;
import Ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player {

    List<Ship> ships;
    Board board;
    Player(Board board){
        this.board = board;
        ships = board.getShips();
    }

    public Board getBoard() {
        return board;
    }

    public boolean isAlive() {
        return ships.stream().anyMatch(ship -> ship.isShipAlive());
    }

}
