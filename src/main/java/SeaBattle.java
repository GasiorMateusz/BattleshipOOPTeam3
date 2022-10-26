import Board.BoardFactory;

public class SeaBattle {

    Game game;
    Input input;
    Display display;
    Player player1;
    Player player2;

    public SeaBattle() {

    }

    public void showMainMenu() {
        boolean tryAgain = true;
        int option;

        while (tryAgain) {
            display.menu();
            option = input.getMenuOption();
            switch (option) {
                case 1:
                    play();
                    break;
                case 2:
                    // TODO: read high score from file
                    break;
                case 3:
                    tryAgain = false;
                    break;
            }
        }
    }


    public void play() {

        do {
            if (!game.playRound(player1)) break;
            if (!game.playRound(player2)) break;
        } while (true);
        display.gameOver();
    }


}
