import Board.BoardFactory;
import Ship.Ship;
import Square.Square;

import java.util.Scanner;

public class Game {

    Input input;
    Display display = new Display();
    Player player1;
    Player player2;




    /**
     * Takes all the actions required to make single player's move.
     *
     * @param enemyPlayer - opposite player
     * @param playerShot - array with shot's coordinates
     *
     * @return false if enemy lost the game, otherwise true
     */

    public boolean playRound (Player enemyPlayer, int[] playerShot) {

        checkShot(enemyPlayer, playerShot);
        display.printSquareStatus(enemyPlayer.board.ocean[playerShot[0]][playerShot[1]]);
        return enemyPlayer.isAlive();
    }

    /**
     * Checks if enemy ship got hit and get
     * @param enemyPlayer - opposite player
     * @param playerShot - array with shot's coordinates
     */


    public void checkShot(Player enemyPlayer, int[] playerShot) {

        int x = playerShot[0];
        int y = playerShot[1];

        enemyPlayer.board.ocean[x][y].updateSquareStatus();
        enemyPlayer.ships.forEach(Ship::updateShipStatus);

    }
    public boolean isGameOver() {
        return false;
    }

    public void checkHighScore(){

    }

}


