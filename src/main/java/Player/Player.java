package Player;

import Board.Board;
import Round.Round;
import Ship.Ship;
import java.util.List;

public class Player {
    private final String name;
    private final List<Ship> ships;
    private final Board board;
    public final Round round;
    public Player(Board board,String name, Round roundType){
        this.name = name;
        this.board = board;
        ships = board.getShips();
        this.round = roundType;
    }
    public String getName(){
        return name;
    }

    public Board getBoard() {
        return board;
    }
    public List<Ship> getShips(){return ships;}

    public boolean isAlive() {
        return ships.stream().anyMatch(ship -> ship.isShipAlive());
    }

}
