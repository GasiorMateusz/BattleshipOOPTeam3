package Utils;

import Ship.ShipType;
import Controler.HighScores;
import Square.Square;
import Square.SquareStatus;

public class Display {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_BLUE = "\u001b[34m";
    private final String ANSI_MAGENTA = "\u001B[35m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_PURPLE = "\u001B[35m";
    private final String ANSI_OCEAN = "\033[48;5;27m";
    private final String ANSI_MISSED = "\033[48;5;15m";
    private final String ANSI_HIT = "\033[48;5;9m";

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }

   public void printShotPositionRepeated(){
       System.out.println(ANSI_RED + "You've already shot here!" + ANSI_RESET);
   }
    public void printGoodByeMessage(){
        System.out.println("Goodbye! Come again!!");
    }
    public void chooseCoordinates() {
        System.out.print(ANSI_GREEN + "Choose coordinates: " + ANSI_RESET);
    }

    public void printPlayerRound(String name) {
        System.out.printf(ANSI_PURPLE + "\u001b[1m  %s ! It's your turn !" + ANSI_RESET + ANSI_BLUE + " >>>\n" + ANSI_RESET, name);
    }

    public void printSquareStatus(Square shot) {
        System.out.println(ANSI_MAGENTA + shot.getStatus() + "!\n" + ANSI_RESET);
    }

    public void printWrongPlacementInputMessage() {
        System.out.println(ANSI_RED + "Wrong input, enter empty square and 1 square away from existing ships" + ANSI_RESET);
    }

    public void printWrongMenuInputMessage() {
        System.out.println(ANSI_RED + "Wrong input, please enter correct number!" + ANSI_RESET);
    }

    public void printWrongShipPlacementMessage() {
        System.out.println(ANSI_RED + "Wrong placement, try to place your ship again" + ANSI_RESET);
    }

    public void printWrongDirectionInputMessage() {
        System.out.println(ANSI_RED + "Wrong input, enter SOUTH, EAST, WEST, NORTH" + ANSI_RESET);
    }

    public void printWrongShotInputMessage() {
        System.out.println(ANSI_RED + "Wrong input. Try again! Square input example: A1!" + ANSI_RESET);
    }

    /**
     * prints the game menu. Allows user to choose the gameplay
     */
    public void mainMenu() {
        System.out.print(
                "Main menu \n" +
                        "\t1. PvP game\n" +
                        "\t2. Play against AI\n" +
                        "\t3. High scores\n" +
                        "\t4. Quit\n"
        );
    }
    public void botDifficulty(){
        System.out.println("Please choose bot difficulty level");
        System.out.println("1: Easy");
        System.out.println("2: Normal");
    }

    public void askForName(String number) {
        System.out.printf("Hey, %s player what's your name ?\n", number);
    }

    /**
     * Clears Bash by moving it's content to the top
     */
    public void clearScreen() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
        System.out.print("\033[H\033[2J");
    }

    public void shipPlacementOption() {
        System.out.println(
                "Choose ship placement mode:\n" +
                        "\t1. manual\n" +
                        "\t2. random"
        );
    }

    /**
     * Prints a board instance.
     * areShipsVisible flag decides whether player can see all ships on the board.
     *
     * @param ocean - instance to be printed
     */

    public void boardWithShips(Square[][] ocean) {
        StringBuilder fields = new StringBuilder();

        for (int row = 0; row < ocean.length; row++) {
            fields.append("\t").append(row + 1);
        }
        fields.append("\n");

        for (int row = 0; row < ocean.length; row++) {
            fields.append((char) (row + 65));
            for (int column = 0; column < ocean.length; column++) {
                fields.append("\t").append(ocean[row][column].display());
            }
            fields.append("\n");
        }
        System.out.println(fields);
    }

    public void printWhichShipIsBeingPlaced(ShipType shipType) {
        System.out.println("Place " + shipType.name() + " with length of " + shipType.getShipLength() + " squares.");
    }
    public void printShipSunk(){
        System.out.println("Ship has been sunk");
    }

    public void askForDirection() {
        System.out.println("Choose direction: North, West, South, East");
    }

    public void boardWithoutShips(Square[][] ocean) {
        StringBuilder fields = new StringBuilder();

        for (int row = 0; row < ocean.length; row++) {
            fields.append("  ").append(row + 1);

        }
        fields.append("\n");


        for (int row = 0; row < ocean.length; row++) {
            fields.append((char) (row + 65));
            for (int column = 0; column < ocean.length; column++) {
                SquareStatus status = ocean[row][column].getStatus();
                if (status.equals(SquareStatus.Missed)) {
                    fields.append(ANSI_MISSED + "  " + ANSI_RESET).append(ANSI_MISSED + " " + ANSI_RESET);
                } else if (status.equals(SquareStatus.Hit)) {
                    fields.append(ANSI_HIT + "  " + ANSI_RESET).append(ANSI_HIT + " " + ANSI_RESET);
                } else {
                    fields.append(ANSI_OCEAN + "  " + ANSI_RESET).append(ANSI_OCEAN + " " + ANSI_RESET);
                }
            }
            fields.append("\n");
        }

        System.out.println(fields);
    }


    /**
     * prints the outcome of the game when it is over.(Congratulations to the winner)
     */
    public void gameOver(String winner, int score) {
        System.out.printf("Congratulations!\n%s has won in %d rounds !%n", winner, score);
    }

    public void printHighScores(HighScores highScores) {
        System.out.println(highScores.highScoresToString());
    }


}
