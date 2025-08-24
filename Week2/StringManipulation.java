import java.util.*;

public class StringManipulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for input
        System.out.println("Enter a sentence with mixed formatting (digits, punctuation, spaces):");
        String input = scanner.nextLine();

        // 1. trim() - Remove leading/trailing spaces
        String trimmed = input.trim();
        System.out.println("\nAfter trim(): " + trimmed);

        // 2. replace() - Replace spaces with underscores
        String replacedSpaces = trimmed.replace(" ", "_");
        System.out.println("After replace() (spaces -> underscores): " + replacedSpaces);

        // 3. replaceAll() - Remove all digits using regex
        String noDigits = trimmed.replaceAll("\\d", "");
        System.out.println("After replaceAll() (remove digits): " + noDigits);

        // 4. split() - Split into words
        String[] words = trimmed.split("\\s+"); // split by one or more spaces
        System.out.println("After split(): " + Arrays.toString(words));

        // Extra processing
        String noPunctuation = removePunctuation(trimmed);
        System.out.println("\nAfter removing punctuation: " + noPunctuation);

        String capitalized = capitalizeWords(noPunctuation);
        System.out.println("After capitalizing words: " + capitalized);

        String reversed = reverseWordOrder(noPunctuation);
        System.out.println("After reversing word order: " + reversed);

        System.out.println("\nWord Frequency:");
        countWordFrequency(noPunctuation);

        scanner.close();
    }

    // Method to remove punctuation
    public static String removePunctuation(String text) {
        return text.replaceAll("[^a-zA-Z0-9\\s]", ""); // keep only letters, digits, and spaces
    }

    // Method to capitalize each word
    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1).toLowerCase())
                      .append(" ");
            }
        }
        return result.toString().trim();
    }

    // Method to reverse word order
    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    // Method to count word frequency
    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
