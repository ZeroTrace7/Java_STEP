package HW;
import java.util.Scanner;

public class TextCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter mathematical expression: ");
        String expression = sc.nextLine().replaceAll("\\s+", "");

        if (!isValidExpression(expression)) {
            System.out.println("Invalid Expression!");
            return;
        }

        int result = evaluateExpression(expression);
        System.out.println("Final Result: " + result);
    }

    public static boolean isValidExpression(String expr) {
        int balance = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (!isDigit(c) && !isOperator(c) && c != '(' && c != ')') return false;
            if (c == '(') balance++;
            if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    public static boolean isDigit(char c) {
        return c >= 48 && c <= 57;
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static int evaluateExpression(String expr) {
        while (expr.contains("(")) {
            int close = expr.indexOf(")");
            int open = expr.lastIndexOf("(", close);
            String inside = expr.substring(open + 1, close);
            int val = evaluateSimple(inside);
            expr = expr.substring(0, open) + val + expr.substring(close + 1);
        }
        return evaluateSimple(expr);
    }

    public static int evaluateSimple(String expr) {
        StringBuilder sb = new StringBuilder(expr);
        int[] numbers = new int[expr.length()];
        char[] ops = new char[expr.length()];
        int numCount = 0, opCount = 0;

        int i = 0;
        while (i < sb.length()) {
            if (isDigit(sb.charAt(i))) {
                int j = i;
                while (j < sb.length() && isDigit(sb.charAt(j))) j++;
                numbers[numCount++] = Integer.parseInt(sb.substring(i, j));
                i = j;
            } else {
                ops[opCount++] = sb.charAt(i);
                i++;
            }
        }

        for (int k = 0; k < opCount; k++) {
            if (ops[k] == '*' || ops[k] == '/') {
                int res = (ops[k] == '*') ? numbers[k] * numbers[k + 1] : numbers[k] / numbers[k + 1];
                numbers[k] = res;
                for (int m = k + 1; m < numCount - 1; m++) numbers[m] = numbers[m + 1];
                for (int m = k; m < opCount - 1; m++) ops[m] = ops[m + 1];
                numCount--;
                opCount--;
                k--;
            }
        }

        int result = numbers[0];
        for (int k = 0; k < opCount; k++) {
            if (ops[k] == '+') result += numbers[k + 1];
            else if (ops[k] == '-') result -= numbers[k + 1];
        }
        return result;
    }
}
