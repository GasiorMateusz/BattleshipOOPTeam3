import Square.Square;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {

    public int getMenuOption(String option) {
        Pattern pattern = Pattern.compile("^[1-4]$");
        if (pattern.matcher(option).matches()) {
            return Integer.parseInt(option);
        }
        else return 5;
    }

    public Square getShot(String squarplayerShotInput) {
        char rowChar = squarplayerShotInput.charAt(0);
        int row = rowChar - 97;
        int column = Integer.parseInt(squarplayerShotInput.substring(1)) - 1;
        return new Square(row, column);
    }

    /**
     * check if shot is within the board
     *
     * @param
     * @return
     */
    public boolean isCorrect(String squarplayerShotInput) {
        Pattern pattern = Pattern.compile("^[a-z][0-9]{1,2}$");
        if ((Integer.parseInt(squarplayerShotInput.substring(1)) - 1) > 10) {
            return false;
        }
        return pattern.matcher(squarplayerShotInput).matches();
    }

}
