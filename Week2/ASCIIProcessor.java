import java.util.Scanner;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user to enter a string
        System.out.println("Enter a string:");
        String input = scanner.nextLine();

        System.out.println("\n--- ASCII Analysis ---");
        for (char ch : input.toCharArray()) {
            int ascii = (int) ch;
            System.out.println("Character: '" + ch + "' | ASCII: " + ascii);

            // Classify
            String type = classifyCharacter(ch);
            System.out.println("Type: " + type);

            // If it's a letter, show upper/lower versions + difference
            if (Character.isLetter(ch)) {
                char upper = Character.toUpperCase(ch);
                char lower = Character.toLowerCase(ch);

                System.out.println("Uppercase: '" + upper + "' | ASCII: " + (int) upper);
                System.out.println("Lowercase: '" + lower + "' | ASCII: " + (int) lower);
                System.out.println("Difference (upper - lower): " + Math.abs((int) upper - (int) lower));
            }

            System.out.println("-----------------------");
        }

        // ASCII Art using codes
        System.out.println("\n--- ASCII Art ---");
        for (int i = 65; i < 70; i++) { // A to E
            for (int j = 65; j <= i; j++) {
                System.out.print((char) j + " ");
            }
            System.out.println();
        }

        // Caesar Cipher
        System.out.println("\n--- Caesar Cipher ---");
        System.out.print("Enter a shift value: ");
        int shift = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String cipherText = caesarCipher(input, shift);
        System.out.println("Original: " + input);
        System.out.println("Encrypted (shift " + shift + "): " + cipherText);

        scanner.close();
    }

    // Method to classify character type
    public static String classifyCharacter(char ch) {
        if (ch >= 'A' && ch <= 'Z') return "Uppercase Letter";
        else if (ch >= 'a' && ch <= 'z') return "Lowercase Letter";
        else if (ch >= '0' && ch <= '9') return "Digit";
        else return "Special Character";
    }

    // Method to convert case using ASCII manipulation
    public static char toggleCase(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch + 32); // uppercase → lowercase
        } else if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 32); // lowercase → uppercase
        }
        return ch; // unchanged if not a letter
    }

    // Caesar Cipher implementation
    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                result.append((char) ('A' + (ch - 'A' + shift) % 26));
            } else if (Character.isLowerCase(ch)) {
                result.append((char) ('a' + (ch - 'a' + shift) % 26));
            } else {
                result.append(ch); // leave digits/special characters unchanged
            }
        }
        return result.toString();
    }
}
