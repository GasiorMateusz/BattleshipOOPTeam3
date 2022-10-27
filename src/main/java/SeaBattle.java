import Board.BoardFactory;


public class SeaBattle {

    Game game = new Game();
    Input input = new Input();
    Display display = new Display();
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
        return new Player(boardFactory.randomPlacement());
    }

    public void showMainMenu() {
        boolean tryAgain = true;
        int option;

        while (tryAgain) {
            display.mainMenu();    ///////
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
        display.printPlayer1Round();
        while (true) {
            display.boardWithoutShips(opponentPlayer.board.getOcean());
            display.chooseCoordinates();
            int[] coordinatesToShot = input.getShot();
            if (game.playRound(opponentPlayer, coordinatesToShot)) {
                display.boardWithoutShips(opponentPlayer.board.getOcean());
                swapPlayers();
                continue;
            }
            display.boardWithoutShips(opponentPlayer.board.getOcean());
            display.gameOver(1);
            break;
        }
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
}