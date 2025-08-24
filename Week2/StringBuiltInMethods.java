public class StringBuiltInMethods {
public static void main(String[] args) {
String sampleText = " Java Programming is Fun and Challenging! ";

// TODO: Use built-in methods to perform the following operations:
// 1. Display original string length including spaces
System.out.println("Length:"+ sampleText.length());

// 2. Remove leading and trailing spaces, show new length


// 3. Find and display the character at index 5

System.out.println("Character at index 5 is :"+ sampleText.charAt(5));

// 4. Extract substring "Programming" from the text

System.out.println("Word:" + sampleText.substring(5, 16));

// 5. Find the index of the word "Fun"

System.out.println("Index of 'Fun': " + sampleText.indexOf("Fun"));

// 6. Check if the string contains "Java" (case-sensitive)

System.out.println("Contains 'Java': " + sampleText.contains("Java"));

// 7. Check if the string starts with "Java" (after trimming)

System.out.println("Starts with 'Java': " + sampleText.trim().startsWith("Java"));

// 8. Check if the string ends with an exclamation mark

System.out.println("Ends with '!': " + sampleText.endsWith("!"));

// 9. Convert the entire string to uppercase
System.out.println("Uppercase: " + sampleText.toUpperCase());
// 10. Convert the entire string to lowercase
System.out.println("Lowercase: " + sampleText.toLowerCase());
// TODO: Create a method that counts vowels using charAt()
// TODO: Create a method that finds all occurrences of a character
// TODO: Display all results in a formatted manner
}
// TODO: Method to count vowels in a string
// public static int countVowels(String text) {
// Your code here
}
// TODO: Method to find all positions of a character
// public static void findAllOccurrences(String text, char target) {
// Your code here