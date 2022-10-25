import Board.BoardFactory;
import Square.Square;

public class Game {

    Input input;
    Display display;
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
        boolean tryAgain = true;
        int option;

        while (tryAgain) {
            display.menu();
            option = input.getMenuOption();
            switch (option) {
                case 1:
                    play();
                    break;
                case 2:
                    // TODO: odczyt najlepszych wyników z pliku
                    break;
                case 3:
                    tryAgain = false;
                    break;
            }
        }
    }

    public void play() {
        boardSetUp();
        do {
            if (playRound(player1)) break;
            if (playRound(player2)) break;
        } while (true);
        display.gameOver();
    }


    public void boardSetUp() {
        //TODO: in future, allow user choose beetween placement ships manually or randomly
        // ustaw obie plansze na sztywno
        BoardFactory boardFactory = new BoardFactory();
        boardFactory.randomPlacement(player1.getBoard());
        boardFactory.randomPlacement(player2.getBoard());

    }



    /**
     * take all the actions required to make single player's move.
     *
     * @param player - player that is moving now
     * @return true if enemy lost the game, otherwise false
     */

    public boolean playRound(Player player) {
        Square playerShot = null;
        Player enemyPlayer;
        if (player == player1) {
            enemyPlayer = player2;
        } else {
            enemyPlayer = player1;
        }

        display.board();
        while (!input.isCorrect(playerShot)){
            playerShot = input.getShot(player);
        }
        checkShot(enemyPlayer, playerShot);
        display.board();
        return enemyPlayer.isAlive();



    }

    public boolean isGameOver() {
        return false;
    }

    public void checkHighScore(){

    }

}
//glowna petla gry wyswietlajaca menu
// liczenie strzalow
// sprawdzanie highscore
// ustawienie plansz na sztywno