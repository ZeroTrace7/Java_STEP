public class StringManipulation{
    public static void main(String[] args) {
    // \
    // 1. String literal

    String str1 = "Java";

    // 2. new String() constructor

    String str2 = new String("Java");

    // 3. Character array

    char[] charArray = {'J', 'a', 'v', 'a'};
    String str3 = new String(charArray);


    // 

    System.out.println("str1 == str2: " + (str1 == str2));         
    System.out.println("str1.equals(str2): " + str1.equals(str2)); 


    // Print the results and explain the difference

    System.out.println("str1 == str3: " + (str1 == str3));         
    System.out.println("str1.equals(str3): " + str1.equals(str3));


    // 

    System.out.println("\nExplanation:");
    System.out.println("== compares references (memory addresses),");
    System.out.println(".equals() compares actual string values.");

    // Programming Quote:
    System.out.println("\nProgramming Quote:");
    System.out.println("\"Code is poetry\" - Unknown");
    System.out.println("Path: C:\\Java\\Projects");
 
    }
    }