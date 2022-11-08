package Controler;

import Board.Board;
import Board.BoardFactory;
import Board.Direction;
import Board.Point;
import Player.ComputerPlayerEasy;
import Player.HumanPlayer;
import Player.Player;
import Ship.ShipType;
import Utils.Display;
import Utils.Input;

public class SeaBattle {

    private final Input input = new Input();
    private final Display display = new Display();
    private final BoardFactory boardFactory = new BoardFactory();
    private Game game;
    private Player player1;
    private Player player2;

    public Display getDisplay() {
        return display;
    }

    public void mainMenu() {
        display.clearScreen();
        int option;
        boolean stillPlaying = true;
        while (stillPlaying) {
            display.mainMenu();
            option = input.getMenuOption();
            switch (option) {
                case 1 -> createAndPlayPvPGame();
                case 2 -> createAndPlayPlayerVsAiGame();
                case 3 -> showHighScore();
                case 4 -> stillPlaying = false;
                default -> display.printWrongMenuInputMessage();
            }
        }
        display.printMessage("Goodbye ! Come again !!");
    }

    private void showHighScore() {
        display.printMessage("To implement");
    }

    private void setUpPvP() {
        player1 = createHumanPlayer("first");
        player2 = createHumanPlayer("second");
    }

    private void setUpPlayerVsAi() {
        player1 = createHumanPlayer("first");
        player2 = createAiPlayer();
    }

    private Player createHumanPlayer(String number) {
        String name = getPlayerName(number);
        Board board = createPlayerBoard();
        return new HumanPlayer(board, name);
    }

    private Player createAiPlayer() {
        return new ComputerPlayerEasy(boardFactory.randomPlacement(), "Computer");
    }

    private String getPlayerName(String number) {
        display.askForName(number);
        return input.getName();
    }

    private void createAndPlayPvPGame() {
        setUpPvP();
        game = new Game(player1, player2);
        game.playGame();
    }

    private void createAndPlayPlayerVsAiGame() {
        setUpPlayerVsAi();
        game = new Game(player1, player2);
        game.playGame();
    }

    private Board createPlayerBoard() {
        display.shipPlacementOption();
        Board board;
        if (input.getPlacementOption() == 1) {
            display.clearScreen();
            board = boardFactory.manualPlacement(this);
            display.boardWithShips(board.getOcean());
        } else {
            board = boardFactory.randomPlacement();
        }
        return board;
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

}