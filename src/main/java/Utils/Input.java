package Utils;

import Board.Board;
import Board.Direction;
import Board.Point;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {
    private final Display display = new Display();
    private final String MATCH_INPUT_FROM_1_TO_5 = "^[1-5]$";
    private final String MATCH_INPUT_FROM_1_TO_2 = "^[1-2]$";
    private final String MATCH_INPUT_COORDINATES_A_TO_J_AND_0_TO_9 = "^[a-j][0-9]{1,2}$";
    private final Scanner scanner = new Scanner(System.in);

    private String getUserInput() {
        return scanner.nextLine();
    }

    public int getMenuOption() {
        String option = getUserInput().strip();
        Pattern pattern = Pattern.compile(MATCH_INPUT_FROM_1_TO_5);
        if (pattern.matcher(option).matches()) {
            return Integer.parseInt(option);
        } else {
            return 6;
        }
    }

    public String getPlayerNameFromUser() {
        return getUserInput().strip();
    }

    public Point getShot() {
        String squarePlayerShotInput;
        boolean isShotInputNotCorrect;

        do {
            squarePlayerShotInput = getUserInput();
            isShotInputNotCorrect = validateIfShotInputIsNotCorrect(squarePlayerShotInput);
            if (isShotInputNotCorrect) {
                display.printWrongShotInputMessage();
            }
        } while (isShotInputNotCorrect);

        return getChosenCoordinates(squarePlayerShotInput);
    }

    private boolean validateIfShotInputIsNotCorrect(String input) {
        if (checkRegex(MATCH_INPUT_COORDINATES_A_TO_J_AND_0_TO_9, input)) {
            return isCoordinateNotInRange(input);
        }
        return true;
    }

    private boolean checkRegex(String regexPattern, String input) {
        Pattern pattern = Pattern.compile(regexPattern);
        return pattern.matcher(input).matches();
    }

    private Point getChosenCoordinates(String input) {
        char rowChar = input.charAt(0);
        int row = rowChar - 97;
        int column = Integer.parseInt(input.substring(1)) - 1;
        return new Point(row, column);
    }
    private boolean isCoordinateNotInRange(String input) {
        return (Integer.parseInt(input.substring(1)) - 1) > 9 || (Integer.parseInt(input.substring(1)) - 1) < 0;
    }

    public Point getPlacementSquare(Board board) {
        Point point;
        boolean isPlacementSquareInputCorrect;
        do {
            point = getShot();
            isPlacementSquareInputCorrect = board.canNewShipBeHere(point);
            if (!isPlacementSquareInputCorrect) {
                display.printWrongPlacementInputMessage();
            }
        } while (!isPlacementSquareInputCorrect);
        return point;
    }

    public int getPlacementOption() {
        String option;
        do {
            option = getUserInput().strip();
        } while (!checkRegex(MATCH_INPUT_FROM_1_TO_2, option));
        return Integer.parseInt(option);
    }

    public Direction getDirection() {
        String directionPlayerInput = getUserInput().strip().toUpperCase();
        boolean isDirectionInputCorrect;
        do {
            isDirectionInputCorrect = isDirectionCorrect(directionPlayerInput);
            if (!isDirectionInputCorrect) {
                display.printWrongDirectionInputMessage();
            }
        } while (!isDirectionInputCorrect);
        return Direction.valueOf(directionPlayerInput);
    }

    public boolean isDirectionCorrect(String input) {
        return Arrays.stream(Direction.values()).anyMatch(direction -> input.equals(direction.name()));
    }
}