interface Printer {
    void connect();
}

interface Scanner {
    void connect();
}

class AllInOneMachine implements Printer, Scanner {
    public void connect() {
        System.out.println("All-in-One Machine connected");
        System.out.println("Ready for printing and scanning");
    }
}

public class PrinterClass {
    public static void main(String[] args) {
        AllInOneMachine machine = new AllInOneMachine();
        machine.connect();
        
        System.out.println();
        
        Printer printer = new AllInOneMachine();
        System.out.println("Using Printer reference:");
        printer.connect();
        
        System.out.println();
        
        Scanner scanner = new AllInOneMachine();
        System.out.println("Using Scanner reference:");
        scanner.connect();
    }
}