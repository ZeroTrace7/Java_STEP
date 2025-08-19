public class StringArrays {

    // Method to find the longest name in the array
    
    public static String findLongestName(String[] names) {
        if (names == null || names.length == 0) return null;

        String longest = names[0];
        for (String name : names) {
            if (name.length() > longest.length()) {
                longest = name;
            }
        }
        return longest;
    }

    // Method to count how many names start with a given letter (case-insensitive)
    public static int countNamesStartingWith(String[] names, char letter) {
        if (names == null) return 0;

        int count = 0;
        char lowerLetter = Character.toLowerCase(letter);

        for (String name : names) {
            if (name != null && !name.isEmpty()) {
                // Check first letter of the name ignoring case
                if (Character.toLowerCase(name.charAt(0)) == lowerLetter) {
                    count++;
                }
            }
        }
        return count;
    }

    // Method to format names from "First Last" to "Last, First"
    public static String[] formatNames(String[] names) {
        if (names == null) return null;

        String[] formatted = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            if (name == null || name.trim().isEmpty()) {
                formatted[i] = "";
                continue;
            }

            String[] parts = name.split(" ");
            if (parts.length < 2) {
                // If name doesn't have at least two parts, leave it as is
                formatted[i] = name;
            } else {
                // Assume last word is last name, the rest is first name(s)
                String lastName = parts[parts.length - 1];
                String firstName = String.join(" ", java.util.Arrays.copyOf(parts, parts.length - 1));
                formatted[i] = lastName + ", " + firstName;
            }
        }
        return formatted;
    }

    public static void main(String[] args) {
        String[] students = {"John Smith", "Alice Johnson", "Bob Brown", "Carol Davis", "David Wilson"};

        // Test findLongestName
        String longestName = findLongestName(students);
        System.out.println("Longest name: " + longestName);

        // Test countNamesStartingWith
        int countA = countNamesStartingWith(students, 'A');
        System.out.println("Number of names starting with 'A': " + countA);

        int countD = countNamesStartingWith(students, 'd');  // testing case-insensitivity
        System.out.println("Number of names starting with 'd': " + countD);

        // Test formatNames
        String[] formattedNames = formatNames(students);
        System.out.println("Formatted names:");
        for (String formattedName : formattedNames) {
            System.out.println(formattedName);
        }
    }
}
