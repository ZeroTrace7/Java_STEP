import java.util.*;

public class PasswordAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of passwords to analyze: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter password " + (i + 1) + ": ");
            String password = scanner.nextLine();
            int score = calculateStrength(password);
            String level = getStrengthLevel(score);
            System.out.println("Password: " + password + " | Score: " + score + " | Strength: " + level);
        }

        System.out.print("\nEnter desired length for strong password generation: ");
        int length = scanner.nextInt();
        String strongPassword = generateStrongPassword(length);
        System.out.println("Generated Strong Password: " + strongPassword);

        scanner.close();
    }

    public static int[] analyzePassword(String password) {
        int upper = 0, lower = 0, digit = 0, special = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            int ascii = (int) ch;
            if (ascii >= 65 && ascii <= 90) upper++;
            else if (ascii >= 97 && ascii <= 122) lower++;
            else if (ascii >= 48 && ascii <= 57) digit++;
            else if (ascii >= 33 && ascii <= 126) special++;
        }
        return new int[]{upper, lower, digit, special};
    }

    public static int calculateStrength(String password) {
        int[] counts = analyzePassword(password);
        int score = 0;
        if (password.length() > 8) score += (password.length() - 8) * 2;
        if (counts[0] > 0) score += 10;
        if (counts[1] > 0) score += 10;
        if (counts[2] > 0) score += 10;
        if (counts[3] > 0) score += 10;
        String lowerPass = password.toLowerCase();
        if (lowerPass.contains("123") || lowerPass.contains("abc") || lowerPass.contains("qwerty"))
            score -= 15;
        return Math.max(score, 0);
    }

    public static String getStrengthLevel(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }

    public static String generateStrongPassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specials = "!@#$%^&*()-_=+[]{}|;:,.<>?";

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specials.charAt(random.nextInt(specials.length())));

        String allChars = upper + lower + digits + specials;
        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < password.length(); i++) {
            chars.add(password.charAt(i));
        }
        Collections.shuffle(chars);
        StringBuilder finalPassword = new StringBuilder();
        for (char c : chars) finalPassword.append(c);

        return finalPassword.toString();
    }
}
