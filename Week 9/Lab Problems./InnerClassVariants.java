interface MathOperation {
    int operate(int a, int b);
}

class Calculator {
    
    static class Operation {
        int add(int a, int b) {
            return a + b;
        }
    }

    void performSubtraction(int a, int b) {
        
        class Subtraction {
            int subtract() {
                return a - b;
            }
        }
        
        Subtraction sub = new Subtraction();
        System.out.println("Subtraction: " + sub.subtract());
    }

    MathOperation getMultiplication() {
        return new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a * b;
            }
        };
    }
}

public class InnerClassVariants {
    public static void main(String[] args) {
        
        Calculator.Operation op = new Calculator.Operation();
        System.out.println("Addition: " + op.add(10, 5));
        
        Calculator calc = new Calculator();
        calc.performSubtraction(10, 5);
        
        MathOperation multiply = calc.getMultiplication();
        System.out.println("Multiplication: " + multiply.operate(10, 5));
    }
}