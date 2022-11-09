package Controler;

import Board.*;
import Player.*;
import Ship.ShipType;
import Utils.Display;
import Utils.Input;

public class SeaBattle {

    private Game game;
    private final Input input = new Input();
    private final Display display = new Display();
    private final BoardFactory boardFactory = new BoardFactory();
    private final PlayerFactory playerFactory = new PlayerFactory();
    private Player player1;
    private Player player2;
    HighScores highScores = new HighScores();

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
                case 5 -> createHiddenGame();
                default -> display.printWrongMenuInputMessage();
            }
        }
        display.printMessage("Goodbye ! Come again !!");
    }

    private void showHighScore() {
        display.printHighScores(highScores);
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
    private void createHiddenGame(){
        setUpAiVsAi();
        game = new Game(player1, player2);
        game.playGame();
    }

    private void setUpPvP() {
        player1 = playerFactory.getPlayer(1, createPlayerBoard(), getPlayerName("first"));
        player2 = playerFactory.getPlayer(1, createPlayerBoard(), getPlayerName("second"));
    }

    private void setUpPlayerVsAi() {
        player1 = playerFactory.getPlayer(1, createPlayerBoard(), getPlayerName("first"));
        player2 = playerFactory.getPlayer(getBotDifficulty() + 1, boardFactory.randomPlacement(), "Computer");
        ((ComputerPlayerEasy) (player2)).setUpOpponentBoard(player1.getBoard());
    }
    private void setUpAiVsAi(){
        player1 = playerFactory.getPlayer(3, boardFactory.randomPlacement(), "Alfred");
        player2 = playerFactory.getPlayer(3, boardFactory.randomPlacement(), "Gordon");

        ((ComputerPlayerEasy) (player1)).setUpOpponentBoard(player2.getBoard());
        ((ComputerPlayerEasy) (player2)).setUpOpponentBoard(player1.getBoard());
    }

    private int getBotDifficulty() {
        display.botDifficulty();
        boolean stillChoosing = true;
        int answer;

        do {
            answer = input.getMenuOption();
            switch (answer) {
                case 1, 2 -> stillChoosing = false;
                default -> display.printWrongMenuInputMessage();
            }
        } while (stillChoosing);

        return answer;
    }

    private String getPlayerName(String number) {
        display.askForName(number);
        return input.getName();
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