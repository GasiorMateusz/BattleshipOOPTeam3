import Board.BoardFactory;
import Square.Square;

public class Game {

    Input input;
    Display display;

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    Player player1;
    Player player2;

    /**
     * check if enemy ship got hit and get
     * @param enemy - opposite player
     * @param playerShot - square chosen by the player
     */
    public void checkShot(Player enemy, Square playerShot) {
        enemy.board.getSquare(playerShot).updateSquareStatus();

    }

    public void showMainMenu() {
        /*
        The Battleship class displays the main menu and allows the user to a start new game, display high scores, and exit.
         */
    }

    public void boardSetUp() {
        //TODO: in future, allow user to placement ships manually
        display.mainMenu();
        int option = input.getMenuOption();
        switch (option) {
            //TODO
            case 1:
                BoardFactory boardFactory = new BoardFactory();
                boardFactory.randomPlacement(player1.getBoard());
                boardFactory.randomPlacement(player2.getBoard());
                break;
            //case 2:...
        }
    }

    public void play() {
        boardSetUp();
        do {
            if (playRound(player1)) break;
            if (playRound(player2)) break;
        } while (true);
        display.gameOver(this);
    }

    /**
     * take all the actions required to make single player's move.
     *
     * @param player - player that is moving now
     * @return true if enemy lost the game, otherwise false
     */
    public boolean playRound(Player player) {
        Player enemyPlayer = player2;
        Square playerShot = null;
        display.board(enemyPlayer.getBoard());
        while (!input.isCorrect(playerShot)) {
            playerShot = input.getShot(player);
        }
        checkShot(enemyPlayer, playerShot);
        display.board(enemyPlayer.getBoard());
        return enemyPlayer.isAlive();

    }

    public boolean isGameOver() {
        return false;
    }


}
