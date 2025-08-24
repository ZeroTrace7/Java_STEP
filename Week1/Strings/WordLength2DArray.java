import java.util.Scanner;
import java.util.ArrayList;

public class WordLength2DArray {

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

    public static String[][] createWordLengthArray(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(getStringLength(words[i]));
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a line of text:");
        String userInput = scanner.nextLine();

        String[] words = splitString(userInput);
        String[][] wordLengthArray = createWordLengthArray(words);

        System.out.println("\nTabular format of words and their lengths:");
        System.out.println("Word\t\tLength");
        System.out.println("----------------------");
        
        for (int i = 0; i < wordLengthArray.length; i++) {
            String word = wordLengthArray[i][0];
            int length = Integer.parseInt(wordLengthArray[i][1]); // Convert back to integer for display
            System.out.println(word + "\t\t" + length);
        }
        
        scanner.close();
    }
}