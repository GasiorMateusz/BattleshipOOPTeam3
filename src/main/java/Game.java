import Board.BoardFactory;
import Square.Square;

public class Game {

    Input input;
    Display display;
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

    }
    public boolean isGameOver() {
        return false;
    }

    public void checkHighScore(){

    }

}


