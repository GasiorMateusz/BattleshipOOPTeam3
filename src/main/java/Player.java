import Board.Board;
import Ship.Ship;
import java.util.List;

public class Player {
    private final String name;
    private final List<Ship> ships;
    private final Board board;
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
    public List<Ship> getShips(){return ships;}

    public boolean isAlive() {
        return ships.stream().anyMatch(ship -> ship.isShipAlive());
    }

}
