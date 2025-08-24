import java.util.Scanner;
import java.util.ArrayList;

public class StringSplitter {

    public static String[] splitString(String str) {
        ArrayList<String> wordsList = new ArrayList<>();
        int startIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                wordsList.add(str.substring(startIndex, i));
                startIndex = i + 1;
            }
        }
        wordsList.add(str.substring(startIndex)); // Add the last word

        return wordsList.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a line of text:");
        String userInput = scanner.nextLine();

        String[] customSplit = splitString(userInput);
        String[] builtInSplit = userInput.split(" ");

        System.out.println("\nWords using custom method:");
        for (String word : customSplit) {
            System.out.println(word);
        }

        System.out.println("\nWords using built-in split() method:");
        for (String word : builtInSplit) {
            System.out.println(word);
        }
        
        scanner.close();
    }
}