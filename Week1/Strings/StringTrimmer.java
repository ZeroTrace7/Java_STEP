import java.util.Scanner;

public class StringTrimmer {

    // Method to find the start and end indices of the trimmed string
    public static int[] findTrimmedIndices(String str) {
        int startIndex = 0;
        int endIndex = str.length() - 1;

        // Find the first non-space character from the beginning
        while (startIndex < str.length() && str.charAt(startIndex) == ' ') {
            startIndex++;
        }

        // Find the first non-space character from the end
        while (endIndex >= startIndex && str.charAt(endIndex) == ' ') {
            endIndex--;
        }
        
        return new int[]{startIndex, endIndex};
    }

    // Method to create a substring
    public static String createSubstring(String str, int start, int end) {
        if (start > end) {
            return "";
        }
        String result = "";
        for (int i = start; i <= end; i++) {
            result += str.charAt(i);
        }
        return result;
    }

    // Method to compare two strings using charAt()
    public static boolean compareStrings(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string with leading/trailing spaces:");
        String userInput = scanner.nextLine();

        // Custom trim logic
        int[] indices = findTrimmedIndices(userInput);
        String customTrimmed = createSubstring(userInput, indices[0], indices[1]);

        // Built-in trim logic
        String builtInTrimmed = userInput.trim();

        System.out.println("Original string: '" + userInput + "'");
        System.out.println("Custom trimmed string: '" + customTrimmed + "'");
        System.out.println("Built-in trimmed string: '" + builtInTrimmed + "'");
        System.out.println("Are the strings equal? " + compareStrings(customTrimmed, builtInTrimmed));
        
        scanner.close();
    }
}