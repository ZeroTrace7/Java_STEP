import java.util.*;

public class CaesarCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        System.out.print("Enter shift value: ");
        int shift = scanner.nextInt();

        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("Original: " + text);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        displayAsciiValues(text, encrypted);

        System.out.println("Decryption valid? " + validate(text, decrypted));

        scanner.close();
    }

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                result.append((char) ('A' + (ch - 'A' + shift) % 26));
            } else if (ch >= 'a' && ch <= 'z') {
                result.append((char) ('a' + (ch - 'a' + shift) % 26));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void displayAsciiValues(String original, String encrypted) {
        System.out.println("\nASCII Values Comparison:");
        for (int i = 0; i < original.length(); i++) {
            char o = original.charAt(i);
            char e = encrypted.charAt(i);
            System.out.println("'" + o + "' (" + (int) o + ") -> '" + e + "' (" + (int) e + ")");
        }
    }

    public static boolean validate(String original, String decrypted) {
        return original.equals(decrypted);
    }
}
