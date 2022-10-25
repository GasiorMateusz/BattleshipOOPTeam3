public class Display {

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
                        "\t3. high scores"
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
     * prints board during:
     * <ul>
     *     <li>manual ship placement process.</li>
     *     <li>the gameplay</li>
     * </ul>
     */
    public void board() {
    }

    public void highScores(){

    }

    /**
     * prints the outcome of the game when it is over.(Congratulations to the winner)
     */
    public void gameOver(Game game) {
        int winner = 2;
        if (game.getPlayer1().isAlive()){
            winner = 1;
        }
        System.out.printf("Congratulations! Player %o has won!%n",winner);
    }

}
