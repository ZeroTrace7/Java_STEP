import java.util.*;

public class ManualSubstringReplace {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the main text:");
        String text = scanner.nextLine();

        System.out.println("Enter the substring to find:");
        String find = scanner.nextLine();

        System.out.println("Enter the replacement substring:");
        String replace = scanner.nextLine();

        String manualResult = manualReplace(text, find, replace);
        String builtInResult = text.replace(find, replace);
        boolean isSame = compareResults(manualResult, builtInResult);

        System.out.println("\n--- RESULTS ---");
        System.out.println("Original Text: " + text);
        System.out.println("Manual Replace Result: " + manualResult);
        System.out.println("Built-in Replace Result: " + builtInResult);
        System.out.println("Are both results same? " + isSame);

        scanner.close();
    }

    public static List<Integer> findOccurrences(String text, String find) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(find);
        while (index != -1) {
            positions.add(index);
            index = text.indexOf(find, index + find.length());
        }
        return positions;
    }

    public static String manualReplace(String text, String find, String replace) {
        if (find.length() == 0) return text;
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (i + find.length() <= text.length() &&
                text.substring(i, i + find.length()).equals(find)) {
                result.append(replace);
                i += find.length();
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static boolean compareResults(String manual, String builtIn) {
        return manual.equals(builtIn);
    }
}
