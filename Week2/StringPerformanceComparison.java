public class StringPerformanceComparison {
    public static void main(String[] args) {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        // Test 1: String concatenation performance
        long startTime, endTime;

        startTime = System.nanoTime();
        String result1 = concatenateWithString(1000);
        endTime = System.nanoTime();
        System.out.println("String concatenation time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        String result2 = concatenateWithStringBuilder(1000);
        endTime = System.nanoTime();
        System.out.println("StringBuilder concatenation time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        String result3 = concatenateWithStringBuffer(1000);
        endTime = System.nanoTime();
        System.out.println("StringBuffer concatenation time: " + (endTime - startTime) + " ns");

        // Approximate memory usage
        System.out.println("\n=== MEMORY USAGE (approx) ===");
        System.out.println("String length: " + result1.length());
        System.out.println("StringBuilder length: " + result2.length());
        System.out.println("StringBuffer length: " + result3.length());

        // Demonstrate StringBuilder methods
        System.out.println("\n=== STRINGBUILDER METHODS ===");
        demonstrateStringBuilderMethods();

        // Demonstrate thread safety difference
        System.out.println("\n=== THREAD SAFETY DEMO ===");
        demonstrateThreadSafety();

        // Practical use cases
        System.out.println("\n=== PRACTICAL USE CASES ===");
        System.out.println("Use String when immutability is required (e.g., constants, keys).");
        System.out.println("Use StringBuilder for fast concatenation in single-threaded programs.");
        System.out.println("Use StringBuffer for safe concatenation in multi-threaded programs.");
    }

    // Method using String concatenation (inefficient)
    public static String concatenateWithString(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "Java " + i + " ";
        }
        return result;
    }

    // Method using StringBuilder (efficient, not thread-safe)
    public static String concatenateWithStringBuilder(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    // Method using StringBuffer (efficient, thread-safe)
    public static String concatenateWithStringBuffer(int iterations) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    // Demonstrating StringBuilder methods
    public static void demonstrateStringBuilderMethods() {
        StringBuilder sb = new StringBuilder("Hello World");
        System.out.println("Original: " + sb);

        sb.append("!!!");
        System.out.println("After append: " + sb);

        sb.insert(5, " Java");
        System.out.println("After insert: " + sb);

        sb.replace(0, 5, "Hi");
        System.out.println("After replace: " + sb);

        sb.delete(3, 8);
        System.out.println("After delete: " + sb);

        sb.reverse();
        System.out.println("After reverse: " + sb);
    }

    // Demonstrating thread safety difference
    public static void demonstrateThreadSafety() {
        StringBuffer stringBuffer = new StringBuffer("Start ");
        StringBuilder stringBuilder = new StringBuilder("Start ");

        Runnable taskBuffer = () -> {
            for (int i = 0; i < 100; i++) {
                stringBuffer.append("B");
            }
        };

        Runnable taskBuilder = () -> {
            for (int i = 0; i < 100; i++) {
                stringBuilder.append("B");
            }
        };

        Thread t1 = new Thread(taskBuffer);
        Thread t2 = new Thread(taskBuffer);
        Thread t3 = new Thread(taskBuilder);
        Thread t4 = new Thread(taskBuilder);

        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();

            t3.start();
            t4.start();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("StringBuffer (thread-safe): " + stringBuffer.length());
        System.out.println("StringBuilder (not thread-safe, may vary): " + stringBuilder.length());
    }
}
