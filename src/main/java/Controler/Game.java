package Controler;

import Board.Point;
import Player.Player;
import Round.Round;
import Utils.Display;

public class Game {
    /**
     * Takes all the actions required to make single player's move.
     *
     * @param enemyPlayer - opposite player
     * @param playerShot - array with shot's coordinates
     * @return false if enemy lost the game, otherwise true
     */

    private final int TIME_TO_WAIT = 2000;
    private final Display display = new Display();
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private Player opponentPlayer;
    private final Round round = new Round();
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
        opponentPlayer = player2;
    }

    public void playGame() {
        while (true) {
            Point point = getPointsToShoot();

            boolean isEnemyAlive = round.playRound(opponentPlayer, point);

            if (isEnemyAlive) {
                continueGame();
                continue;
            }

            gameOver();
            break;
        }
    }
    private Point getPointsToShoot(){
        display.printPlayerRound(currentPlayer.getName());
        display.boardWithoutShips(opponentPlayer.getBoard().getOcean());
        return currentPlayer.getCoordinates();
    }

    private void continueGame() {
        waitForSpecifiedTime(TIME_TO_WAIT);
        swapPlayers();
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

    private void gameOver() {
        display.boardWithoutShips(opponentPlayer.getBoard().getOcean());
        display.gameOver(currentPlayer.getName());
    }


    private void waitForSpecifiedTime(int timeToWait) {
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException ie) {
            display.printMessage(ie.getMessage());
        }
    }
}