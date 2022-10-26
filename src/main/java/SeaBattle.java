import Board.Board;
import Board.BoardFactory;
import Square.Square;

public class SeaBattle {
    Player player1;
    Player player2;
    BoardFactory boardFactory = new BoardFactory();

    Game game = new Game();

    public SeaBattle() {
        player1 = createPlayer();
        // player2 = createPlayer();

        Square[][] ocean = player1.getBoard().ocean;

        for (int row = 0; row < ocean.length; row++) {
            for (int col = 0; col < ocean.length; col++) {
                System.out.print(ocean[row][col].getStatus().getCharacter());
            }
            System.out.println();
        }


    }

    public Player createPlayer() {
        /*
        player chooses manual vs random
         */
        return new Player(boardFactory.testPlacement());
    }

    public void playGame() {

        while (true) {
            game.playRound();
        }
    }
}


