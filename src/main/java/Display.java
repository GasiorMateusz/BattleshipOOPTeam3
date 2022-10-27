
import Square.Square;
import Square.SquareStatus;


public class Display {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_MAGENTA = "\u001B[35m";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printPlayer1Round() {
        System.out.println(ANSI_CYAN + "\u001b[1m Player 1 " + ANSI_RESET + ANSI_BLUE + " >>>\n" + ANSI_RESET);
    }

    public void printPlayer2Round() {
        System.out.println(ANSI_CYAN + "\u001b[1m Player 2 " + ANSI_RESET + ANSI_BLUE + " >>>\n" + ANSI_RESET);
    }

    public void printSquareStatus(Square shot) {
        System.out.println(ANSI_MAGENTA + shot.getStatus() + "!\n" + ANSI_RESET);
    }

    public void printWrongMenuInputMessage() {
        System.out.println(ANSI_RED + "Wrong input, enter answer from 1 to 4!" + ANSI_RESET);
    }

    public void printWrongShotInputMessage() {
        System.out.println(ANSI_RED + "Wrong input. Try again! Shot input example: A1!" + ANSI_RESET);
    }

    /**
     * prints the game menu. Allows user to choose the gameplay
     */
    public void mainMenu() {
        System.out.print(
                "Main menu \n" +
                        "\t1. PvP game\n" +
                        "\t2. Play against AI\n" +
                        "\t3. high scores\n" +
                        "\t4. quit\n"
        );
    }

    public void shipPlacementOption() {
        System.out.println(
                "Choose ship placement mode:\n" +
                        "\t1. manual\n" +
                        "\t2. random\n"
        );
    }


    /**
     * Prints a board instance.
     * areShipsVisible flag decides whether player can see all ships on the board.
     *
     * @param ocean - instance to be printed
     */

    public void boardWithShips (Square[][] ocean) {
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
    public void boardWithoutShips(Square[][] ocean) {
        boolean areShipsVisible = true;
        StringBuilder fields = new StringBuilder();

        for (int row = 0; row < ocean.length; row++) {
            fields.append("\t").append(row + 1);
        }
        fields.append("\n");


        for (int row = 0; row < ocean.length; row++) {
            fields.append((char) (row + 65));
            for (int column = 0; column < ocean.length; column++) {
                SquareStatus status = ocean[row][column].getStatus();
                if (status.equals(SquareStatus.Missed) || status.equals(SquareStatus.Hit)) {
                    fields.append("\t").append(ocean[row][column].display());
                } else {
                    fields.append("\t").append(" ");
                }
            }
            fields.append("\n");
        }

        System.out.println(fields);
    }
    public void highScores() {

    }

    /**
     * prints the outcome of the game when it is over.(Congratulations to the winner)
     */
    public void gameOver(int winner) {
        System.out.printf("Congratulations! Player %o has won!%n", winner);
    }

}
