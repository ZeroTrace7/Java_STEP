import java.util.Scanner;
import java.util.ArrayList;

public class VowelConsonantFinder {

    // Method to check if a character is a vowel, consonant, or not a letter
    public static String getCharType(char ch) {
        char lowerCaseCh = ch;
        if (ch >= 'A' && ch <= 'Z') {
            lowerCaseCh = (char) (ch + 32); // Convert to lowercase using ASCII
        }

        if (lowerCaseCh >= 'a' && lowerCaseCh <= 'z') {
            if (lowerCaseCh == 'a' || lowerCaseCh == 'e' || lowerCaseCh == 'i' || lowerCaseCh == 'o' || lowerCaseCh == 'u') {
                return "Vowel";
            } else {
                return "Consonant";
            }
        } else {
            return "Not a Letter";
        }
    }

    // Method to find and return characters and their types in a 2D array
    public static String[][] findVowelsAndConsonants(String str) {
        ArrayList<String[]> resultList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String type = getCharType(ch);
            resultList.add(new String[]{String.valueOf(ch), type});
        }
        return resultList.toArray(new String[0][0]);
    }

    // Method to display the 2D array in tabular format
    public static void displayTable(String[][] data) {
        System.out.println("Character\tType");
        System.out.println("--------------------");
        for (String[] row : data) {
            System.out.printf("%-10s\t%s\n", row[0], row[1]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String userInput = scanner.nextLine();

        String[][] charTable = findVowelsAndConsonants(userInput);
        displayTable(charTable);

        scanner.close();
    }
}