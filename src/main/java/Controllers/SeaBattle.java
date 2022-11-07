package Controllers;

import Board.BoardFactory;
import Board.Point;
import Utils.Display;
import Utils.Input;

public class SeaBattle {

    private final Game game = new Game();
    private final Input input = new Input();
    private final Display display = new Display();
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player opponentPlayer;
    private final BoardFactory boardFactory = new BoardFactory();

    public void mainMenu() {
        int option;
        while (true) {
            display.mainMenu();
            option = input.getMenuOption();
            switch (option) {
                case 1 -> playGame();
                case 2 -> playGame();
                case 3 -> showHighScore();
                case 4 -> exitGame();
                default -> display.printWrongMenuInputMessage();
            }
        }
    }

    private void showHighScore() {
        display.printMessage("To implement");
    }

    private void exitGame() {
        display.printMessage("Goodbye ! Come again !!");
        System.exit(0);
    }

    private void setUpGame() {
        player1 = createPlayer("first");
        currentPlayer = player1;
        player2 = createPlayer("second");
        opponentPlayer = player2;
    }

    private Player createPlayer(String number) {
        return new Player(boardFactory.randomPlacement(), getPlayerName(number));
    }
    private String getPlayerName(String number){
        display.askForName(number);
        return input.getName();
    }

    private void playGame() {
        setUpGame();
        while (true) {
            Point pointToShoot = askForCoordinates();
            if (game.playRound(opponentPlayer, pointToShoot)) {
                continueGame(pointToShoot);
                continue;
            }
            gameOver();
            break;
        }
    }

    private void gameOver() {
        display.boardWithoutShips(opponentPlayer.getBoard().getOcean());
        display.gameOver(currentPlayer.getName());
        exitGame();
    }

    private void continueGame(Point point) {
        display.boardWithoutShips(opponentPlayer.getBoard().getOcean());
        display.printSquareStatus(opponentPlayer.getBoard().getOcean()[point.getX()][point.getY()]);
        waitForFewSeconds();
        swapPlayers();
    }

    private Point askForCoordinates() {
        display.printPlayerRound(currentPlayer.getName());
        display.boardWithoutShips(opponentPlayer.getBoard().getOcean());
        display.chooseCoordinates();
        return input.getShot();
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

    private void waitForFewSeconds() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ie) {
            display.printMessage(ie.getMessage());
        }
    }
}