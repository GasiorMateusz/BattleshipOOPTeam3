import Board.BoardFactory;

public class SeaBattle {

    Game game;
    Input input;
    Display display;
    Player player1;
    Player player2;

    public SeaBattle(Game game) {
        this.game = game;
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
        boardSetUp();
        do {
            if (!game.playRound(player1)) break;
            if (!game.playRound(player2)) break;
        } while (true);
        display.gameOver();
    }

    public void boardSetUp() {
        //TODO: in future, allow user choose beetween placement ships manually or randomly
        BoardFactory boardFactory = new BoardFactory();
        boardFactory.randomPlacement(player1.getBoard());
        boardFactory.randomPlacement(player2.getBoard());

    }

}
