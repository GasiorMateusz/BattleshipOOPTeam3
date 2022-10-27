import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {
    Display display = new Display();
    Scanner scanner = new Scanner(System.in);
    boolean isShotInputCorrect = false;
    String squarplayerShotInput = null;

    public int getMenuOption() {
        String option = scanner.nextLine().strip();
        Pattern pattern = Pattern.compile("^[1-4]$");
        if (pattern.matcher(option).matches()) {
            return Integer.parseInt(option);
        }
        else {
            return 5;
        }
    }

    public int[] getShot() {
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

    private int[] getChosenCoordinates() {
        char rowChar = squarplayerShotInput.charAt(0);
        int row = rowChar - 97;
        int column = Integer.parseInt(squarplayerShotInput.substring(1)) - 1;
        return new int[]{row, column};
    }

    private boolean isCoordinateNumberOk() {
        return (Integer.parseInt(squarplayerShotInput.substring(1)) - 1) > 9 || (Integer.parseInt(squarplayerShotInput.substring(1)) - 1) < 0;
    }

    private boolean checkRegex() {
        squarplayerShotInput = scanner.nextLine();
        Pattern pattern = Pattern.compile("^[a-j][0-9]{1,2}$");
        return pattern.matcher(squarplayerShotInput).matches();
    }
}