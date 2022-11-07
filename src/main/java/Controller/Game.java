package Controller;

import Board.Point;
import Player.Player;
import Ship.Ship;
public class Game {

    public boolean playRound (Player enemyPlayer, Point point) {

        enemyPlayer.getBoard().getOcean()[point.getX()][point.getY()].updateSquareStatus();
        enemyPlayer.getShips().forEach(Ship::updateShipStatus);
        return enemyPlayer.isAlive();
    }
    public void checkHighScore(){
    }
}