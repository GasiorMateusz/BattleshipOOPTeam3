package Controler;

import Player.Player;

import java.io.*;
import java.util.*;

public class HighScores {

    /**
     * Reads high scores from file highScores.txt. Creates new file if highScores.txt does not exist.
     *
     * @return Map<Integer, String> with results and player names
     */
    public static final String FILE_NAME = "highScores.txt";

    public Map<Integer, String> readHighScoresFromFile () {
        String fileName = HighScores.FILE_NAME;
        File file = new File(fileName);
        boolean fileExists = file.exists();
        Map<Integer, String> highScores = new TreeMap<>();

        if (!fileExists) {
            createNewFile(fileName, file);
        } else {
            convertHighScoresToHashMap(file, highScores);
        }
        return highScores;
    }

    private static void convertHighScoresToHashMap(File file, Map<Integer, String> highScores) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(";");
                highScores.put(Integer.valueOf(split[1]), split[0]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File highScores.txt not found");
        }
    }

    private static void createNewFile(String fileName, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create new " + fileName + " file");
        }
    }

    /**
     * Returns high scores from highScores.txt as String.
     *
     * @return high scores as String
     */

    public String highScoresToString() {
        Map<Integer, String> highScoresFromFile = readHighScoresFromFile();

        StringBuilder highScores = new StringBuilder();
        String firstLine = String.format("%-15s | %-15s", "Player", "Score");
        highScores.append(firstLine + "\n");

        for (Map.Entry<Integer, String> integerStringEntry : highScoresFromFile.entrySet()) {
            String line = String.format("%-15s | %-15d",integerStringEntry.getValue(), integerStringEntry.getKey());
            highScores.append(line + "\n");
        }
        return String.valueOf(highScores);
    }

    /**
     * Checks if the player's score is a high score (top 10 of current high scores in highScores.txt).
     *
     * @param player - player that has won the game
     *
     */
    public void checkIfPlayerResultIsHighScore(Player player, int playerScore) {
        String playerName = player.getName();

        Map<Integer, String> highScores = readHighScoresFromFile();
        Set<Integer> keys = highScores.keySet();

        if (keys.size() >= 10) {
            Integer worstScore = 0;
            for (Integer key : keys) {
                if (worstScore < key) {
                    worstScore = key;
                }
            }
            highScores.remove(worstScore);
            highScores.put(playerScore, playerName);
        } else {
            highScores.put(playerScore, playerName);
        }
        saveHighScoresFile(highScores);
    }

    /**
     * Saves new high score to highScores.txt file.
     *
     * @param highScores
     */

    public void saveHighScoresFile(Map<Integer, String> highScores) {
        String fileName = HighScores.FILE_NAME;
        StringBuilder highScoresBuilder = new StringBuilder();

        for (Map.Entry<Integer, String> integerStringEntry : highScores.entrySet()) {
            String line = String.format("%s;%d",integerStringEntry.getValue(), integerStringEntry.getKey());
            highScoresBuilder.append(line + "\n");
        }

        try (FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter writer = new BufferedWriter(fileWriter)) {

            writer.write(String.valueOf(highScoresBuilder));
            System.out.println("High Scores saved");
        } catch (IOException e) {
            System.out.println("Failed to save " + fileName);
        }
    }
}