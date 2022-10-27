import Board.Point;
import Ship.Ship;
public class Game {
    /**
     * Takes all the actions required to make single player's move.
     *
     * @param enemyPlayer - opposite player
     * @param playerShot - array with shot's coordinates
     *
     * @return false if enemy lost the game, otherwise true
     */

    public boolean playRound (Player enemyPlayer, Point point) {

        enemyPlayer.getBoard().getOcean()[point.getX()][point.getY()].updateSquareStatus();
        enemyPlayer.getShips().forEach(Ship::updateShipStatus);
        return enemyPlayer.isAlive();
    }
    public void checkHighScore(){
    }
}