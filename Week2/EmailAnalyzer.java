import java.util.*;

public class EmailAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of emails: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        List<String> emails = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter email " + (i + 1) + ": ");
            emails.add(scanner.nextLine());
        }

        int validCount = 0, invalidCount = 0;
        Map<String, Integer> domainCount = new HashMap<>();
        int totalUsernameLength = 0;

        for (String email : emails) {
            if (validateEmail(email)) {
                validCount++;
                String[] parts = extractComponents(email);
                String username = parts[0];
                String domain = parts[1];
                String domainName = parts[2];
                String extension = parts[3];

                System.out.println("\nEmail: " + email);
                System.out.println("Username: " + username);
                System.out.println("Domain: " + domain);
                System.out.println("Domain Name: " + domainName);
                System.out.println("Extension: " + extension);

                domainCount.put(domainName, domainCount.getOrDefault(domainName, 0) + 1);
                totalUsernameLength += username.length();
            } else {
                invalidCount++;
                System.out.println("\nEmail: " + email + " -> INVALID");
            }
        }

        System.out.println("\n=== STATISTICS ===");
        System.out.println("Valid Emails: " + validCount);
        System.out.println("Invalid Emails: " + invalidCount);

        if (!domainCount.isEmpty()) {
            String mostCommonDomain = Collections.max(domainCount.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println("Most Common Domain: " + mostCommonDomain);
            double avgUsernameLength = (double) totalUsernameLength / validCount;
            System.out.println("Average Username Length: " + avgUsernameLength);
        }

        scanner.close();
    }

    public static boolean validateEmail(String email) {
        int atPos = email.indexOf('@');
        int lastAtPos = email.lastIndexOf('@');
        if (atPos == -1 || atPos != lastAtPos) return false;
        int dotPos = email.indexOf('.', atPos);
        if (dotPos == -1) return false;
        String username = email.substring(0, atPos);
        String domain = email.substring(atPos + 1);
        return !username.isEmpty() && !domain.isEmpty();
    }

    public static String[] extractComponents(String email) {
        int atPos = email.indexOf('@');
        int dotPos = email.lastIndexOf('.');
        String username = email.substring(0, atPos);
        String domain = email.substring(atPos + 1);
        String domainName = email.substring(atPos + 1, dotPos);
        String extension = email.substring(dotPos + 1);
        return new String[]{username, domain, domainName, extension};
    }
}
