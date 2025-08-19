import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Ask user for their full name
        System.out.print("Enter your full name (first and last): ");
        String fullName = scanner.nextLine().trim();

        // 2. Ask user for their favorite programming language
        System.out.print("Enter your favorite programming language: ");
        String language = scanner.nextLine().trim();

        // 3. Ask user for a sentence about their programming experience
        System.out.print("Enter a sentence about your programming experience: ");
        String experience = scanner.nextLine();

        // === PROCESSING ===

        // 1. Extract first and last name separately
        String firstName = "";
        String lastName = "";

        String[] nameParts = fullName.split(" ");
        if (nameParts.length >= 2) {
            firstName = nameParts[0];
            lastName = nameParts[1];
        } else {
            firstName = fullName;
            lastName = "(not provided)";
        }

        // 2. Count total characters in the sentence (excluding spaces)
        int charCount = experience.replace(" ", "").length();

        // 3. Convert programming language to uppercase
        String languageUpper = language.toUpperCase();

        // 4. Display formatted summary
        System.out.println("\n=== Summary ===");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Favorite Language: " + languageUpper);
        System.out.println("Experience Sentence: " + experience);
        System.out.println("Total characters in experience (excluding spaces): " + charCount);

        scanner.close();
    }
}
