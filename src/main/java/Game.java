import Board.BoardFactory;
import Square.Square;

public class Game {

    Input input;
    Display display;
    Player player1;
    Player player2;

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }




    /**
     * take all the actions required to make single player's move.
     *
     * @param player - player that is moving now
     * @return false if enemy lost the game, otherwise true
     */

    public boolean playRound(Player player) {
        Square playerShot = null;
        Player enemyPlayer;

        if (player == player1) {
            enemyPlayer = player2;
        } else {
            enemyPlayer = player1;
        }

        display.board(player.board.getOcean());
        while (!input.isCorrect(playerShot)){
            playerShot = input.getShot(player);
        }
        checkShot(enemyPlayer, playerShot);
        display.board(player.board.getOcean());
        return enemyPlayer.isAlive();

    }

    /**
     * check if enemy ship got hit and get
     * @param enemy - opposite player
     * @param playerShot - square chosen by the player
     */

    public void checkShot(Player enemy, Square playerShot) {
        enemy.board.getSquare(playerShot).updateSquareStatus();

    }


    public boolean isGameOver() {
        return false;
    }

    public void checkHighScore(){

    }

}


