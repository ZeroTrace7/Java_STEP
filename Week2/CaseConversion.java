import java.util.*;

public class CaseConversion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter text:");
        String text = scanner.nextLine();

        String upper = toUpperCase(text);
        String lower = toLowerCase(text);
        String title = toTitleCase(text);

        System.out.println("Uppercase: " + upper);
        System.out.println("Lowercase: " + lower);
        System.out.println("Title Case: " + title);

        scanner.close();
    }

    public static char toUpperChar(char ch) {
        if (ch >= 'a' && ch <= 'z') return (char)(ch - 32);
        return ch;
    }

    public static char toLowerChar(char ch) {
        if (ch >= 'A' && ch <= 'Z') return (char)(ch + 32);
        return ch;
    }

    public static String toUpperCase(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) sb.append(toUpperChar(text.charAt(i)));
        return sb.toString();
    }

    public static String toLowerCase(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) sb.append(toLowerChar(text.charAt(i)));
        return sb.toString();
    }

    public static String toTitleCase(String text) {
        StringBuilder sb = new StringBuilder();
        boolean newWord = true;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                sb.append(ch);
                newWord = true;
            } else {
                if (newWord) {
                    sb.append(toUpperChar(ch));
                    newWord = false;
                } else {
                    sb.append(toLowerChar(ch));
                }
            }
        }
        return sb.toString();
    }
}
