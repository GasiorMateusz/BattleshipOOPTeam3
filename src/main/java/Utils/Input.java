package Utils;

import Board.Point;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {
    private final Display display = new Display();
    private final Scanner scanner = new Scanner(System.in);
    boolean isShotInputCorrect = false;
    String squarePlayerShotInput = null;

    public int getMenuOption() {
        String option = scanner.nextLine().strip();
        Pattern pattern = Pattern.compile("^[1-4]$");
        if (pattern.matcher(option).matches()) {
            return Integer.parseInt(option);
        } else {
            return 5;
        }
    }

    public String getName() {
        return scanner.nextLine().strip();
    }

    public Point getShot() {
        do {
            isShotInputCorrect = isCorrect();
            if (!isShotInputCorrect) {
                display.printWrongShotInputMessage();
            }
        } while (!isShotInputCorrect);
        return getChosenCoordinates();
    }

    /**
     * check if shot is within the board
     *
     * @param
     * @return
     */
    public boolean isCorrect() {
        if (!checkRegex()) {
            return false;
        }
        return !isCoordinateNumberOk();
    }

    private Point getChosenCoordinates() {
        char rowChar = squarePlayerShotInput.charAt(0);
        int row = rowChar - 97;
        int column = Integer.parseInt(squarePlayerShotInput.substring(1)) - 1;
        return new Point(row, column);
    }

    private boolean isCoordinateNumberOk() {
        return (Integer.parseInt(squarePlayerShotInput.substring(1)) - 1) > 9 || (Integer.parseInt(squarePlayerShotInput.substring(1)) - 1) < 0;
    }

    private boolean checkRegex() {
        squarePlayerShotInput = scanner.nextLine();
        Pattern pattern = Pattern.compile("^[a-j][0-9]{1,2}$");
        return pattern.matcher(squarePlayerShotInput).matches();
    }
}