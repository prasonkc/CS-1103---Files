/**
 * This program is a text analyzer that reads multiple lines of user input and processes the raw text to perform different operations including counting characters in the text, counting words, finding the most common character, computing the frequency of a given character or word, and more.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Utility methods for analyzing a block of text.
 *
 * @author Prason KC
 */
class TextUtility {
    /** The raw text */
    private final String text;

    /**
     * Constructor for the provided text.
     *
     * @param text the input text to analyze
     */
    public TextUtility(String text) {
        this.text = text;
    }

    /**
     * Returns the original input text.
     *
     * @return the original text
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the total number of characters in the text.
     *
     * @return the character count
     */
    public int getCharacterCount() {
        return text.length();
    }

    /**
     * Returns the number of words in the text.
     *
     * @return the word count
     */
    public int getWordCount() {
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            return 0;
        }
        return trimmed.split(" ").length;
    }

    /**
     * Returns the most common character in the text.
     *
     * @return the most common character excluding white spaces.
     */
    public char calculateMostCommonCharacter() {
        Map<Character, Integer> characterMap = new HashMap<>();

        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isWhitespace(c)) {
                continue;
            }
            Integer count = characterMap.get(c);
            if (count == null) {
                characterMap.put(c, 1);
            } else {
                characterMap.put(c, count + 1);
            }
        }

        char mostCommon = ' ';
        int count = 0;
        for (Map.Entry<Character, Integer> entry : characterMap.entrySet()) {
            if (entry.getValue() > count) {
                mostCommon = entry.getKey();
                count = entry.getValue();
            }
        }
        return mostCommon;
    }

    /**
     * Counts how often a character occurs.
     *
     * @param target the character to count, as a single character string
     * @return the number of occurrences of the character
     */
    public int calculateCharacterFrequency(String target) {
        int frequency = 0;

        for (char character : text.toLowerCase().toCharArray()) {
            if (target.equalsIgnoreCase(String.valueOf(character))) {
                frequency++;
            }
        }
        return frequency;
    }

    /**
     * Counts how often a word occurs.
     *
     * @param target the word to count
     * @return the number of occurrences of the word
     */
    public int calculateWordFrequency(String target) {
        int frequency = 0;
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            return 0;
        }

        for (String word : trimmed.split(" ")) {
            if (word.equalsIgnoreCase(target)) {
                frequency++;
            }
        }
        return frequency;
    }

    /**
     * Returns the number of unique words in the text.
     *
     * @return the count of distinct words
     */
    public int calculateUniqueWords() {
        Set<String> wordSet = new HashSet<>();
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            return 0;
        }

        for (String word : trimmed.toLowerCase().split(" ")) {
            wordSet.add(word);
        }

        return wordSet.size();
    }
}

/**
 * Menu-driven application.
 *
 * @author Prason KC
 */
public class AS1 {
    /**
     * Launches the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println(
                "Enter your text below "
                        + "(Enter a blank line after you finish input)");
        System.out.print("--> ");

        Scanner scanner = new Scanner(System.in);
        StringBuilder text = new StringBuilder();

        String line;
        do {
            line = scanner.nextLine();
            text.append(line).append(" ");
        } while (!line.isEmpty());

        TextUtility textUtility = new TextUtility(text.toString());

        int menuChoice = 1;
        do {
            printMenu();
            try {
                menuChoice = scanner.nextInt();
                scanner.nextLine();
                handleInput(scanner, textUtility, menuChoice);
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println(
                        "Invalid Input. Try entering a valid option.");
            }
        } while (menuChoice != 0);
    }

    /**
     * Prints the menu options.
     */
    private static void printMenu() {
        System.out.println("----------------------------------------");
        System.out.println("1. Get your input text");
        System.out.println("2. Get the word count");
        System.out.println("3. Get the most common character");
        System.out.println("4. Get any character's frequency");
        System.out.println("5. Get any word's frequency");
        System.out.println("6. Get the number of unique words");
        System.out.println("7. Get the total character count");
        System.out.println("0. Quit");
    }

    /**
     * Executes the action for a given menu choice.
     *
     * @param scanner the scanner used to read user input
     * @param textUtility the utility class
     * @param menuChoice the menu choice selected by the user
     */
    private static void handleInput(
            Scanner scanner,
            TextUtility textUtility,
            int menuChoice
    ) {
        switch (menuChoice) {
            case 1 -> System.out.println(textUtility.getText());
            case 2 -> System.out.println(
                    "Word Count: " + textUtility.getWordCount());
            case 3 -> System.out.println(
                    "Most common character: "
                            + textUtility.calculateMostCommonCharacter());
            case 4 -> {
                System.out.println("Enter a character to lookup");
                String input = String.valueOf(
                        scanner.next().toLowerCase().charAt(0));
                scanner.nextLine();
                System.out.println(
                        "Character frequency: "
                                + textUtility.calculateCharacterFrequency(
                                input));
            }
            case 5 -> {
                System.out.println("Enter a word to lookup");
                String input = scanner.next();
                scanner.nextLine();
                System.out.println(
                        "Word frequency: "
                                + textUtility.calculateWordFrequency(input));
            }
            case 6 -> System.out.println(
                    "Number of unique words: "
                            + textUtility.calculateUniqueWords());
            case 7 -> System.out.println(
                    "Total number of characters: "
                            + textUtility.getCharacterCount());
            case 0 -> System.out.println("Quitting program...");
            default -> System.out.println("Invalid Option. Try again!");
        }
    }

}
