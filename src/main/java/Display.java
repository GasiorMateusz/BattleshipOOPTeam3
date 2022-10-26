import Board.Board;
import Square.Square;

public class Display {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printSquareStatus(Square shot) {
        System.out.println(shot.getStatus() + "!\n");
    }


    public void printWrongMenuInputMessage() {
        System.out.println("Wrong input, enter answer from 1 to 4!");
    }

    public void printWrongShotInputMessage() {
        System.out.println("Wrong input. Try again! Shot input example: A1!");
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

    public void board (Square[][] ocean) {

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

    public void highScores(){

    }

    /**
     * prints the outcome of the game when it is over.(Congratulations to the winner)
     */
    public void gameOver(int winner) {
        System.out.printf("Congratulations! Player %o has won!%n",winner);
    }

}
