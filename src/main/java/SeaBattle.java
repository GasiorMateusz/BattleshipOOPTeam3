import Board.BoardFactory;

import java.util.Scanner;

public class SeaBattle {

    Game game = new Game();
    Input input;
    Display display;
    Player player1;
    Player player2;
    Player currentPlayer;
    Player opponentPlayer;
    BoardFactory boardFactory = new BoardFactory();

    public SeaBattle() {

    }

    private void setUpGame() {
        player1 = createPlayer();
        currentPlayer = player1;
        player2 = createPlayer();
        opponentPlayer = player2;

    }

    private Player createPlayer() {
        return new Player(boardFactory.testPlacement());
    }

    public void showMainMenu() {
        boolean tryAgain = true;
        int option;

        while (tryAgain) {
            display.mainMenu();
            option = input.getMenuOption();
            switch (option) {
                case 1:
                    play();
                    break;
                case 2:
                    play();
                    break;
                case 3:
                    //TODO show highScores
                case 4:
                    tryAgain = false;
                    break;
                case 5:
                    break;
            }
        }
    }


    public void play() {
        setUpGame();

        while (true) {
            display.board(opponentPlayer.board.getOcean());
            display.printMessage("Choose coordinates");
            int[] coordinatesToShot = input.getShot();
            if (game.playRound(opponentPlayer, coordinatesToShot)) {
                display.board(opponentPlayer.board.getOcean());
                swapPlayers();
                continue;
            }
            display.board(opponentPlayer.board.getOcean());
            display.gameOver(1);
            break;
        }
    }

    private void swapPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
            opponentPlayer = player1;
        } else {
            currentPlayer = player1;
            opponentPlayer = player2;
        }


    }
}