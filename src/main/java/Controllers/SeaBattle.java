package Controllers;

import Board.Board;
import Board.BoardFactory;
import Board.Direction;
import Board.Point;
import Ship.ShipType;
import Utils.Display;
import Utils.Input;

public class SeaBattle {

    private final Game game = new Game();
    private final Input input = new Input();
    private final Display display = new Display();
    private final BoardFactory boardFactory = new BoardFactory();
    private Player player1;
    private Player player2;

    private Player currentPlayer;
    private Player opponentPlayer;

    public Display getDisplay() {
        return display;
    }

    public void mainMenu() {
        int option;
        while (true) {
            display.mainMenu();
            option = input.getMenuOption();
            switch (option) {
                case 1 -> playGame(); //PvP
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
        String name = getPlayerName("first");
        Board board = createPlayerBoard();
        player1 = new Player(name, board);
        currentPlayer = player1;

        name = getPlayerName("second");
        board = createPlayerBoard();
        player2 = new Player(name, board);
        opponentPlayer = player2;
    }

    private Board createPlayerBoard() {
        display.shipPlacementOption();
        Board board;
        if (input.getPlacementOption() == 1) {
            board = boardFactory.manualPlacement(this);
            display.boardWithShips(board.getOcean());
        } else {
            board = boardFactory.randomPlacement();
        }
        return board;
    }

    private String getPlayerName(String number) {
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
        return input.getSquare();
    }

    public Point askForPlacementCoordinates(ShipType shipType, Board board) {
        display.boardWithShips(board.getOcean());
        display.printWhichShipIsBeingPlaced(shipType);
        display.chooseCoordinates();
        return input.getPlacementSquare(board);
    }

    public Direction askForPlacementDirection() {
        display.askForDirection();
        return input.getDirection();
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