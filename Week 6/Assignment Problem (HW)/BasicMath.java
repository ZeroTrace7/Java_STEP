// Base BasicMath class with overloaded methods
public class BasicMath {
    // Fields
    protected String calculatorName;
    protected int operationCount;
    
    // Constructor
    public BasicMath(String calculatorName) {
        this.calculatorName = calculatorName;
        this.operationCount = 0;
    }
    
    // Overloaded calculate() methods for different operations
    
    // 1. Addition with two integers
    public int calculate(int a, int b) {
        operationCount++;
        int result = a + b;
        System.out.println("Addition: " + a + " + " + b + " = " + result);
        return result;
    }
    
    // 2. Subtraction with String operation and two integers
    public int calculate(String operation, int a, int b) {
        operationCount++;
        int result = 0;
        
        if (operation.equals("subtract")) {
            result = a - b;
            System.out.println("Subtraction: " + a + " - " + b + " = " + result);
        } else if (operation.equals("multiply")) {
            result = a * b;
            System.out.println("Multiplication: " + a + " * " + b + " = " + result);
        } else {
            System.out.println("Unknown operation: " + operation);
        }
        
        return result;
    }
    
    // 3. Division with two double values
    public double calculate(double a, double b) {
        operationCount++;
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return 0.0;
        }
        
        double result = a / b;
        System.out.println("Division: " + a + " / " + b + " = " + result);
        return result;
    }
    
    // 4. Sum of array
    public int calculate(int[] numbers) {
        operationCount++;
        int sum = 0;
        
        System.out.print("Sum of array [");
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
            System.out.print(numbers[i]);
            if (i < numbers.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] = " + sum);
        
        return sum;
    }
    
    // 5. Three number operations
    public int calculate(int a, int b, int c) {
        operationCount++;
        int result = a + b + c;
        System.out.println("Three number addition: " + a + " + " + b + " + " + c + " = " + result);
        return result;
    }
    
    // Common methods
    public void showOperationCount() {
        System.out.println(calculatorName + " has performed " + operationCount + " operations");
    }
    
    public void reset() {
        operationCount = 0;
        System.out.println("Operation count reset to 0");
    }
    
    public String getCalculatorName() {
        return calculatorName;
    }
}

// AdvancedMath extends BasicMath and adds more overloaded methods
class AdvancedMath extends BasicMath {
    private boolean scientificMode;
    
    // Constructor
    public AdvancedMath(String calculatorName) {
        super(calculatorName + " Advanced"); // Call parent constructor
        this.scientificMode = true;
        System.out.println("Advanced Math Calculator created");
    }
    
    // Inherit all parent calculate() methods and add new ones
    
    // 6. Power operation (new overloaded method)
    public double calculate(String operation, double base, double exponent) {
        operationCount++;
        double result = 0.0;
        
        if (operation.equals("power")) {
            result = Math.pow(base, exponent);
            System.out.println("Power: " + base + "^" + exponent + " = " + result);
        } else if (operation.equals("root")) {
            result = Math.pow(base, 1.0/exponent);
            System.out.println("Root: " + exponent + "th root of " + base + " = " + result);
        } else {
            System.out.println("Unknown advanced operation: " + operation);
        }
        
        return result;
    }
    
    // 7. Square root (single double parameter with operation)
    public double calculate(String operation, double number) {
        operationCount++;
        double result = 0.0;
        
        if (operation.equals("sqrt")) {
            result = Math.sqrt(number);
            System.out.println("Square root of " + number + " = " + result);
        } else if (operation.equals("square")) {
            result = number * number;
            System.out.println("Square of " + number + " = " + result);
        } else if (operation.equals("abs")) {
            result = Math.abs(number);
            System.out.println("Absolute value of " + number + " = " + result);
        } else {
            System.out.println("Unknown single number operation: " + operation);
        }
        
        return result;
    }
    
    // 8. Average of array (new overloaded method)
    public double calculate(double[] numbers) {
        operationCount++;
        double sum = 0.0;
        
        System.out.print("Average of array [");
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
            System.out.print(numbers[i]);
            if (i < numbers.length - 1) {
                System.out.print(", ");
            }
        }
        
        double average = sum / numbers.length;
        System.out.println("] = " + average);
        
        return average;
    }
    
    // 9. Factorial (special case with boolean flag)
    public long calculate(int number, boolean isFactorial) {
        operationCount++;
        
        if (isFactorial) {
            long factorial = 1;
            for (int i = 1; i <= number; i++) {
                factorial *= i;
            }
            System.out.println("Factorial of " + number + " = " + factorial);
            return factorial;
        } else {
            System.out.println("Boolean flag not set for factorial");
            return 0;
        }
    }
    
    // 10. Percentage calculation
    public double calculate(double value, double percentage, String operation) {
        operationCount++;
        double result = 0.0;
        
        if (operation.equals("percent")) {
            result = (value * percentage) / 100.0;
            System.out.println(percentage + "% of " + value + " = " + result);
        } else if (operation.equals("percentIncrease")) {
            result = value + (value * percentage / 100.0);
            System.out.println(value + " increased by " + percentage + "% = " + result);
        } else if (operation.equals("percentDecrease")) {
            result = value - (value * percentage / 100.0);
            System.out.println(value + " decreased by " + percentage + "% = " + result);
        }
        
        return result;
    }
    
    // Advanced-specific methods
    public void toggleScientificMode() {
        scientificMode = !scientificMode;
        System.out.println("Scientific mode: " + (scientificMode ? "ON" : "OFF"));
    }
    
    public boolean isScientificMode() {
        return scientificMode;
    }
    
    // Override parent method to show advanced info
    @Override
    public void showOperationCount() {
        super.showOperationCount(); // Call parent method
        System.out.println("Scientific mode: " + (scientificMode ? "Enabled" : "Disabled"));
    }
}

// Demo class to test method overloading inheritance
class MathOperationsDemo {
    public static void main(String[] args) {
        System.out.println("Math Operations Inheritance Demo");
        System.out.println();
        
        // Create BasicMath calculator
        BasicMath basicCalc = new BasicMath("Basic Calculator");
        
        System.out.println("Testing BasicMath overloaded methods:");
        
        // Test different overloaded calculate methods
        basicCalc.calculate(10, 5);                    // Addition (int, int)
        basicCalc.calculate("subtract", 15, 7);        // Subtraction (String, int, int)
        basicCalc.calculate("multiply", 4, 6);         // Multiplication (String, int, int)
        basicCalc.calculate(20.0, 4.0);                // Division (double, double)
        
        int[] numbers = {1, 2, 3, 4, 5};
        basicCalc.calculate(numbers);                   // Sum array (int[])
        basicCalc.calculate(2, 3, 4);                  // Three numbers (int, int, int)
        
        basicCalc.showOperationCount();
        System.out.println();
        
        // Create AdvancedMath calculator
        AdvancedMath advancedCalc = new AdvancedMath("Scientific Calculator");
        
        System.out.println("Testing inherited BasicMath methods in AdvancedMath:");
        
        // Test inherited methods
        advancedCalc.calculate(100, 25);               // Inherited addition
        advancedCalc.calculate("subtract", 50, 30);    // Inherited subtraction
        advancedCalc.calculate(100.0, 3.0);            // Inherited division
        
        int[] moreNumbers = {10, 20, 30};
        advancedCalc.calculate(moreNumbers);           // Inherited array sum
        
        System.out.println("\nTesting NEW overloaded methods in AdvancedMath:");
        
        // Test new overloaded methods
        advancedCalc.calculate("power", 2.0, 8.0);     // Power (String, double, double)
        advancedCalc.calculate("sqrt", 16.0);          // Square root (String, double)
        advancedCalc.calculate("square", 7.0);         // Square (String, double)
        advancedCalc.calculate("abs", -15.0);          // Absolute value (String, double)
        
        double[] doubleArray = {2.5, 3.7, 4.2, 5.1};
        advancedCalc.calculate(doubleArray);           // Average (double[])
        
        advancedCalc.calculate(5, true);               // Factorial (int, boolean)
        
        advancedCalc.calculate(200.0, 15.0, "percent");        // Percentage
        advancedCalc.calculate(100.0, 20.0, "percentIncrease"); // Percent increase
        
        advancedCalc.showOperationCount();
        
        System.out.println("\nTesting advanced-specific methods:");
        advancedCalc.toggleScientificMode();
        
        System.out.println("\nDemonstrating method overloading:");
        System.out.println("Same method name 'calculate' with different parameters:");
        System.out.println("- calculate(int, int) -> Addition");
        System.out.println("- calculate(String, int, int) -> Named operation");
        System.out.println("- calculate(double, double) -> Division");
        System.out.println("- calculate(int[]) -> Array sum");
        System.out.println("- calculate(String, double) -> Single value operations");
        System.out.println("- calculate(double[]) -> Array average");
        System.out.println("- And more...");
    }
}