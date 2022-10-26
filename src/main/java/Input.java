import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {
    Display display;
    Scanner scanner = new Scanner(System.in);
    boolean isShotInputCorrect = false;
    String squarplayerShotInput = null;

    public int getMenuOption() {
        String option = scanner.nextLine();
        Pattern pattern = Pattern.compile("^[1-4]$");
        if (!pattern.matcher(option).matches()) {
            return Integer.parseInt(option);
        }
        else {
            display.printWrongMenuInputMessage();
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
        char rowChar = squarplayerShotInput.charAt(0);
        int row = rowChar - 97;
        int column = Integer.parseInt(squarplayerShotInput.substring(1)) - 1;
        return new int[] {row, column};
    }

    /**
     * check if shot is within the board
     *
     * @param
     * @return
     */
    public boolean isCorrect() {
        squarplayerShotInput = scanner.nextLine();
        Pattern pattern = Pattern.compile("^[a-z][0-9]{1,2}$");
        if ((Integer.parseInt(squarplayerShotInput.substring(1)) - 1) > 10) {
            return false;
        }
        return pattern.matcher(squarplayerShotInput).matches();
    }
}