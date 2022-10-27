import Board.BoardFactory;

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
        player1 = createPlayer();
        currentPlayer = player1;
        player2 = createPlayer();
        opponentPlayer = player2;
    }

    private Player createPlayer() {
        return new Player(boardFactory.randomPlacement(), "Elon Musk");
    }

    private void playGame() {
        setUpGame();
        display.printPlayer1Round();
        while (true) {
            askForCoordinates();
            if (game.playRound(opponentPlayer, input.getShot())) {
                continueGame();
                continue;
            }
            gameOver();
            break;
        }
    }

    private void gameOver() {
        display.boardWithoutShips(opponentPlayer.board.getOcean());
        display.gameOver(currentPlayer.getName());
        exitGame();
    }

    private void continueGame() {
        display.boardWithoutShips(opponentPlayer.board.getOcean());
        waitForFewSeconds();
        swapPlayers();
    }

    private void askForCoordinates() {
        display.boardWithoutShips(opponentPlayer.board.getOcean());
        display.chooseCoordinates();
    }

    private void swapPlayers() {
        if (currentPlayer == player1) {
            display.printPlayer2Round();
            currentPlayer = player2;
            opponentPlayer = player1;
        } else {
            display.printPlayer1Round();
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