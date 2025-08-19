import java.util.Scanner;

public class TextProcessor {
    
    // Method to clean and validate input
    public static String cleanInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }
        
        // Remove extra spaces and convert to proper case
        String cleaned = input.trim();
        
        // Replace multiple spaces with single space
        while (cleaned.contains("  ")) {
            cleaned = cleaned.replace("  ", " ");
        }
        
        // Convert to proper case (first letter uppercase, rest lowercase)
        if (cleaned.length() > 0) {
            cleaned = cleaned.substring(0, 1).toUpperCase() + 
                     cleaned.substring(1).toLowerCase();
        }
        
        return cleaned;
    }
    
    // Method to analyze text
    public static void analyzeText(String text) {
        if (text.isEmpty()) {
            System.out.println("No text to analyze.");
            return;
        }
        
        // Count characters (including spaces)
        int charCount = text.length();
        
        // Count characters (excluding spaces)
        int charCountNoSpaces = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ') {
                charCountNoSpaces++;
            }
        }
        
        // Count words
        String[] words = text.split(" ");
        int wordCount = 0;
        for (String word : words) {
            if (!word.trim().isEmpty()) {
                wordCount++;
            }
        }
        
        // Count sentences (look for . ! ?)
        int sentenceCount = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '.' || c == '!' || c == '?') {
                sentenceCount++;
            }
        }
        if (sentenceCount == 0 && !text.trim().isEmpty()) {
            sentenceCount = 1; // At least one sentence if there's text
        }
        
        // Find longest word
        String longestWord = "";
        for (String word : words) {
            // Remove punctuation from word for comparison
            String cleanWord = "";
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (Character.isLetter(c)) {
                    cleanWord += c;
                }
            }
            if (cleanWord.length() > longestWord.length()) {
                longestWord = cleanWord;
            }
        }
        
        // Find most common character (excluding spaces)
        char mostCommonChar = ' ';
        int maxCount = 0;
        
        for (int i = 0; i < text.length(); i++) {
            char currentChar = Character.toLowerCase(text.charAt(i));
            if (currentChar != ' ' && Character.isLetter(currentChar)) {
                int count = 0;
                for (int j = 0; j < text.length(); j++) {
                    if (Character.toLowerCase(text.charAt(j)) == currentChar) {
                        count++;
                    }
                }
                if (count > maxCount) {
                    maxCount = count;
                    mostCommonChar = currentChar;
                }
            }
        }
        
        // Display statistics
        System.out.println("\n=== TEXT ANALYSIS ===");
        System.out.println("Characters (with spaces): " + charCount);
        System.out.println("Characters (without spaces): " + charCountNoSpaces);
        System.out.println("Words: " + wordCount);
        System.out.println("Sentences: " + sentenceCount);
        System.out.println("Longest word: " + longestWord);
        if (mostCommonChar != ' ') {
            System.out.println("Most common character: '" + mostCommonChar + "' (appears " + maxCount + " times)");
        }
    }
    
    // Method to create word array and sort alphabetically
    public static String[] getWordsSorted(String text) {
        if (text.isEmpty()) {
            return new String[0];
        }
        
        // Split text into words
        String[] words = text.split(" ");
        
        // Clean words (remove punctuation) and count valid words
        int validWordCount = 0;
        for (int i = 0; i < words.length; i++) {
            String cleanWord = "";
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (Character.isLetter(c)) {
                    cleanWord += Character.toLowerCase(c);
                }
            }
            words[i] = cleanWord;
            if (!cleanWord.isEmpty()) {
                validWordCount++;
            }
        }
        
        // Create array with only valid words
        String[] cleanWords = new String[validWordCount];
        int index = 0;
        for (String word : words) {
            if (!word.isEmpty()) {
                cleanWords[index] = word;
                index++;
            }
        }
        
        // Simple bubble sort (beginner-friendly)
        for (int i = 0; i < cleanWords.length - 1; i++) {
            for (int j = 0; j < cleanWords.length - 1 - i; j++) {
                if (cleanWords[j].compareTo(cleanWords[j + 1]) > 0) {
                    // Swap
                    String temp = cleanWords[j];
                    cleanWords[j] = cleanWords[j + 1];
                    cleanWords[j + 1] = temp;
                }
            }
        }
        
        return cleanWords;
    }
    
    // Method to search for a specific word
    public static boolean searchWord(String[] words, String searchTerm) {
        String lowerSearchTerm = searchTerm.toLowerCase();
        for (String word : words) {
            if (word.toLowerCase().equals(lowerSearchTerm)) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== TEXT PROCESSOR ===");
        
        // 1. Ask user for a paragraph of text
        System.out.print("Enter a paragraph of text: ");
        String userInput = scanner.nextLine();
        
        // 2. Clean and validate the input
        String cleanedText = cleanInput(userInput);
        
        if (cleanedText.isEmpty()) {
            System.out.println("No valid text entered. Please try again.");
            scanner.close();
            return;
        }
        
        System.out.println("\nCleaned text: " + cleanedText);
        
        // 3. Analyze the text
        analyzeText(cleanedText);
        
        // 4. Show words in alphabetical order
        String[] sortedWords = getWordsSorted(cleanedText);
        
        if (sortedWords.length > 0) {
            System.out.println("\n=== WORDS IN ALPHABETICAL ORDER ===");
            for (int i = 0; i < sortedWords.length; i++) {
                System.out.print(sortedWords[i]);
                if (i < sortedWords.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
            
            // 5. Allow user to search for specific words
            System.out.println("\n=== WORD SEARCH ===");
            String searchAgain = "yes";
            
            while (searchAgain.toLowerCase().equals("yes") || searchAgain.toLowerCase().equals("y")) {
                System.out.print("Enter a word to search for: ");
                String searchWord = scanner.nextLine();
                
                if (searchWord.trim().isEmpty()) {
                    System.out.println("Please enter a valid word.");
                    continue;
                }
                
                boolean found = searchWord(sortedWords, searchWord.trim());
                
                if (found) {
                    System.out.println("✓ Word '" + searchWord.trim().toLowerCase() + "' was found in the text!");
                } else {
                    System.out.println("✗ Word '" + searchWord.trim().toLowerCase() + "' was not found in the text.");
                }
                
                System.out.print("Do you want to search for another word? (yes/no): ");
                searchAgain = scanner.nextLine().trim();
            }
        }
        
        System.out.println("\nThank you for using Text Processor!");
        scanner.close();
    }
}