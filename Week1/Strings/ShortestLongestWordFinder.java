import java.util.Scanner;
import java.util.ArrayList;

public class ShortestLongestWordFinder {

    // Method to find string length without length()
    public static int getStringLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            return count;
        }
    }

    // Method to split string without split()
    public static String[] splitString(String str) {
        ArrayList<String> wordsList = new ArrayList<>();
        int startIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                if (startIndex < i) { // Handle multiple spaces
                    wordsList.add(str.substring(startIndex, i));
                }
                startIndex = i + 1;
            }
        }
        if (startIndex < str.length()) {
            wordsList.add(str.substring(startIndex));
        }
        return wordsList.toArray(new String[0]);
    }

    // Method to create a 2D array of words and lengths
    public static String[][] createWordLengthArray(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(getStringLength(words[i]));
        }
        return result;
    }

    // Method to find shortest and longest word lengths
    public static int[] findShortestAndLongest(String[][] wordLengthArray) {
        int minLength = Integer.MAX_VALUE;
        int maxLength = Integer.MIN_VALUE;

        for (int i = 0; i < wordLengthArray.length; i++) {
            int currentLength = Integer.parseInt(wordLengthArray[i][1]);
            if (currentLength < minLength) {
                minLength = currentLength;
            }
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
        }
        return new int[]{minLength, maxLength};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a line of text:");
        String userInput = scanner.nextLine();

        String[] words = splitString(userInput);
        if (words.length == 0 || (words.length == 1 && words[0].isEmpty())) {
            System.out.println("No words to process.");
            scanner.close();
            return;
        }

        String[][] wordLengthArray = createWordLengthArray(words);
        int[] minMaxLengths = findShortestAndLongest(wordLengthArray);

        System.out.println("Shortest word length: " + minMaxLengths[0]);
        System.out.println("Longest word length: " + minMaxLengths[1]);

        scanner.close();
    }
}