import java.util.Scanner;

public class TextCompression {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text to compress: ");
        String text = sc.nextLine();

        char[] uniqueChars = new char[text.length()];
        int[] frequencies = new int[text.length()];
        int uniqueCount = countFrequencies(text, uniqueChars, frequencies);

        String[][] mapping = generateCodes(uniqueChars, frequencies, uniqueCount);

        String compressed = compressText(text, mapping);
        double ratio = (double) compressed.length() / text.length();

        System.out.println("\n=== Compression Results ===");
        System.out.println("Original Text: " + text);
        System.out.println("Compressed Text: " + compressed);
        System.out.println("Compression Ratio: " + ratio);

        System.out.println("\nMapping Table:");
        for (int i = 0; i < uniqueCount; i++) {
            System.out.println("'" + mapping[i][0] + "' -> " + mapping[i][1]);
        }
    }

    public static int countFrequencies(String text, char[] chars, int[] freqs) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = findCharIndex(chars, count, c);
            if (index == -1) {
                chars[count] = c;
                freqs[count] = 1;
                count++;
            } else {
                freqs[index]++;
            }
        }
        return count;
    }

    public static int findCharIndex(char[] chars, int count, char c) {
        for (int i = 0; i < count; i++) {
            if (chars[i] == c) return i;
        }
        return -1;
    }

    public static String[][] generateCodes(char[] chars, int[] freqs, int count) {
        String[][] mapping = new String[count][2];
        for (int i = 0; i < count; i++) {
            mapping[i][0] = String.valueOf(chars[i]);
            mapping[i][1] = createCode(freqs[i], i);
        }
        return mapping;
    }

    public static String createCode(int frequency, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) ('A' + (index % 26)));
        if (frequency > 5) sb.append("0");
        else if (frequency > 2) sb.append("1");
        else sb.append("2");
        return sb.toString();
    }

    public static String compressText(String text, String[][] mapping) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (int j = 0; j < mapping.length; j++) {
                if (mapping[j][0].charAt(0) == c) {
                    sb.append(mapping[j][1]);
                    break;
                }
            }
        }
        return sb.toString();
    }
}
