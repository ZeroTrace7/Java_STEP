import java.util.*;

public class SpellChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();

        String[] dictionary = {"hello", "world", "java", "programming", "openai", "chatgpt", "spell", "checker"};

        List<String> words = splitSentence(sentence);

        System.out.println("\nSpell Check Results:");
        for (String word : words) {
            if (isInDictionary(word, dictionary)) {
                System.out.println(word + " -> Correct");
            } else {
                String suggestion = findClosestWord(word, dictionary);
                if (suggestion != null) {
                    System.out.println(word + " -> Incorrect, did you mean: " + suggestion + "?");
                } else {
                    System.out.println(word + " -> Incorrect, no suggestion");
                }
            }
        }
        scanner.close();
package HW;
import java.util.*;
    public class SpellChecker {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a sentence: ");
            String sentence = scanner.nextLine();
            String[] dictionary = {"hello", "world", "java", "programming", "openai", "chatgpt", "spell", "checker"};
            List<String> words = splitSentence(sentence);
            System.out.println("\nSpell Check Results:");
            for (String word : words) {
                if (isInDictionary(word, dictionary)) {
                    System.out.println(word + " -> Correct");
                } else {
                    String suggestion = findClosestWord(word, dictionary);
                    if (suggestion != null) {
                        System.out.println(word + " -> Incorrect, did you mean: " + suggestion + "?");
                    } else {
                        System.out.println(word + " -> Incorrect, no suggestion");
                    }
                }
            }
            scanner.close();
        }
        public static List<String> splitSentence(String sentence) {
            List<String> words = new ArrayList<>();
            int start = 0;
            for (int i = 0; i < sentence.length(); i++) {
                char ch = sentence.charAt(i);
                if (ch == ' ' || ch == ',' || ch == '.' || ch == '!' || ch == '?') {
                    if (start < i) {
                        words.add(sentence.substring(start, i).toLowerCase());
                    }
                    start = i + 1;
                }
            }
            if (start < sentence.length()) {
                words.add(sentence.substring(start).toLowerCase());
            }
            return words;
        }
        public static boolean isInDictionary(String word, String[] dictionary) {
            for (String dictWord : dictionary) {
                if (word.equals(dictWord)) return true;
            }
            return false;
        }
        public static int stringDistance(String w1, String w2) {
            int len1 = w1.length();
            int len2 = w2.length();
            int distance = Math.abs(len1 - len2);
            int minLen = Math.min(len1, len2);
            for (int i = 0; i < minLen; i++) {
                if (w1.charAt(i) != w2.charAt(i)) {
                    distance++;
                }
            }
            return distance;
        }
        public static String findClosestWord(String word, String[] dictionary) {
            int minDistance = Integer.MAX_VALUE;
            String closest = null;
            for (String dictWord : dictionary) {
                int dist = stringDistance(word, dictWord);
                if (dist < minDistance) {
                    minDistance = dist;
                    closest = dictWord;
                }
            }
            if (minDistance <= 2) {
                return closest;
            }
            return null;
        }
    }
                int distance = Math.abs(len1 - len2);
                int minLen = Math.min(len1, len2);
                for (int i = 0; i < minLen; i++) {
                    if (w1.charAt(i) != w2.charAt(i)) {
                        distance++;
                    }
                }
                return distance;
            }
            public static String findClosestWord(String word, String[] dictionary) {
                int minDistance = Integer.MAX_VALUE;
                String closest = null;
                for (String dictWord : dictionary) {
                    int dist = stringDistance(word, dictWord);
                    if (dist < minDistance) {
                        minDistance = dist;
                        closest = dictWord;
                    }
                }
                if (minDistance <= 2) {
                    return closest;
                }
                return null;
            }
        }
