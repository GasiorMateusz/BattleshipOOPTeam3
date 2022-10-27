import Board.Board;
import Board.BoardFactory;
import Ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;

    List<Ship> ships;
    Board board;
    Player(Board board,String name){
        this.name = name;
        this.board = board;
        ships = board.getShips();
    }
    public String getName(){
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isAlive() {
        return ships.stream().anyMatch(ship -> ship.isShipAlive());
    }

}
