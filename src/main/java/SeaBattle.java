import Board.BoardFactory;
import Player.Player;
import Round.*;
import Utils.Display;
import Utils.Input;

public class SeaBattle {

    private Game game;
    private final Input input = new Input();
    private final Display display = new Display();
    private Player player1;
    private Player player2;
    private final BoardFactory boardFactory = new BoardFactory();

    public void mainMenu() {
        int option;
        while (true) {
            display.mainMenu();
            option = input.getMenuOption();
            switch (option) {
                case 1 -> createAndPlayPvPGame();
                case 2 -> createAndPlayPlayerVsAiGame();
                case 3 -> showHighScore();
                case 4 -> exitGame();
                default -> display.printWrongMenuInputMessage();
            }
        }

    }

    private void showHighScore() {
        display.printMessage("To implement");
    }

    private void exitGame() {
        display.printMessage("Goodbye ! Come again !!");
        System.exit(0);
    }

    private void setUpPvP() {
        player1 = createHumanPlayer("first");
        player2 = createHumanPlayer("second");
    }
    private void setUpPlayerVsAi() {
        player1 = createHumanPlayer("first");
        player2 = createAiPlayer();
    }

    private Player createHumanPlayer(String number) {
        return new Player(boardFactory.randomPlacement(), getPlayerName(number), new HumanRound() {
        });
    }
    private Player createAiPlayer() {
        return new Player(boardFactory.randomPlacement(),"Computer" , new AiRound() {
        });
    }
    private String getPlayerName(String number){
        display.askForName(number);
        return input.getName();
    }
    private void createAndPlayPvPGame(){
        setUpPvP();
        game = new Game(player1,player2);
        game.playGame();
    }
    private void createAndPlayPlayerVsAiGame(){
        setUpPlayerVsAi();
        game = new Game(player1,player2);
        game.playGame();
    }










}