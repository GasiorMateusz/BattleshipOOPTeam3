import Board.Board;
import Square.Square;

public class Display {

    public static final String WRONG_INPUT_MENU = "Wrong input, Try again!";
    public static final String WRONG_INPUT_SHOT = "Wrong input. Try again! Input example: A1";

    public void printMessage(String message) {
        System.out.println(message);
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

    public void shipPlacementOption(){
        System.out.println(
                "Choose ship placement mode:\n" +
                        "\t1. manual\n" +
                        "\t2. random\n"
        );
    }

    /**
     * Prints a board instance.
     * @param ocean - instance to be printed
     */
    public void board(Square[][] ocean) {
        StringBuilder row = new StringBuilder();
        for (Square[] boardRow: ocean
             ) {
            for (Square square: boardRow
                 ) {
                row.append(square.display());
            }
            row.append("\n");
        }
        System.out.println(row);
    }

    public void highScores(){

    }

    /**
     * prints the outcome of the game when it is over.(Congratulations to the winner)
     */
    public void gameOver(int winner) {
        System.out.printf("Congratulations! Player %o has won!%n",winner);
    }

}
