public class Counter {
    static int count = 0;
    private int instanceId;
    
    Counter() {
        count++;
        instanceId = count;
        System.out.println("Counter object #" + instanceId + " created");
    }
    
    public static int getCount() {
        return count;
    }
    
    public static void displayTotalCount() {
        System.out.println("Total Counter objects created: " + count);
    }
    
    public int getInstanceId() {
        return instanceId;
    }
    
    public void displayInstanceInfo() {
        System.out.println("Instance ID: " + instanceId + ", Total objects: " + count);
    }
    
    public static void main(String[] args) {
        System.out.println("COUNTER STATIC KEYWORD DEMONSTRATION");
        System.out.println();
        
        System.out.println("Initial count: " + Counter.getCount());
        System.out.println();
        
        System.out.println("Creating Counter objects:");
        Counter counter1 = new Counter();
        System.out.println("Count after creating counter1: " + Counter.getCount());
        
        Counter counter2 = new Counter();
        System.out.println("Count after creating counter2: " + Counter.getCount());
        
        Counter counter3 = new Counter();
        System.out.println("Count after creating counter3: " + Counter.getCount());
        
        System.out.println();
        Counter.displayTotalCount();
        
        System.out.println();
        System.out.println("Creating multiple objects in a loop:");
        for (int i = 0; i < 5; i++) {
            Counter temp = new Counter();
        }
        
        System.out.println();
        Counter.displayTotalCount();
        
        System.out.println();
        System.out.println("Creating Counter array:");
        Counter[] counterArray = new Counter[3];
        for (int i = 0; i < counterArray.length; i++) {
            counterArray[i] = new Counter();
        }
        
        System.out.println();
        System.out.println("Final count using static method: " + Counter.getCount());
        
        System.out.println();
        System.out.println("Individual instance information:");
        counter1.displayInstanceInfo();
        counter2.displayInstanceInfo();
        counter3.displayInstanceInfo();
        
        System.out.println();
        System.out.println("Array instance information:");
        for (int i = 0; i < counterArray.length; i++) {
            System.out.print("Array[" + i + "] - ");
            counterArray[i].displayInstanceInfo();
        }
        
        System.out.println();
        System.out.println("STATIC KEYWORD CONCEPTS DEMONSTRATED:");
        System.out.println("1. Static variable 'count' shared across all instances");
        System.out.println("2. Static method 'getCount()' accessible without object creation");
        System.out.println("3. Static variable incremented in constructor");
        System.out.println("4. Static method called using ClassName.methodName()");
        System.out.println("5. All instances share the same static variable value");
        
        System.out.println();
        System.out.println("TOTAL OBJECTS CREATED: " + Counter.getCount());
    }
}