class MovieTicket {
    // Instance variables
    private String movieName;
    private String theatreName;
    private int seatNumber;
    private double price;
    
    // Default constructor - assigns "Unknown" movie
    public MovieTicket() {
        this.movieName = "Unknown";
        this.theatreName = "Unknown";
        this.seatNumber = 0;
        this.price = 0.0;
    }
    
    // Constructor with movie name - assigns default price = 200
    public MovieTicket(String movieName) {
        this.movieName = movieName;
        this.theatreName = "Unknown";
        this.seatNumber = 0;
        this.price = 200.0;
    }
    
    // Constructor with movie name and seat number - assigns default theatre "PVR"
    public MovieTicket(String movieName, int seatNumber) {
        this.movieName = movieName;
        this.theatreName = "PVR";
        this.seatNumber = seatNumber;
        this.price = 200.0;
    }
    
    // Full constructor - sets all details
    public MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }
    
    // Method to print ticket details
    public void printTicket() {
        System.out.println("==================== MOVIE TICKET ====================");
        System.out.println("Movie Name    : " + movieName);
        System.out.println("Theatre Name  : " + theatreName);
        System.out.println("Seat Number   : " + (seatNumber == 0 ? "Not Assigned" : seatNumber));
        System.out.println("Price         : ₹" + price);
        System.out.println("======================================================");
        System.out.println();
    }
    
    // Getter methods (optional but good practice)
    public String getMovieName() { return movieName; }
    public String getTheatreName() { return theatreName; }
    public int getSeatNumber() { return seatNumber; }
    public double getPrice() { return price; }
    
    // Setter methods (optional but good practice)
    public void setMovieName(String movieName) { this.movieName = movieName; }
    public void setTheatreName(String theatreName) { this.theatreName = theatreName; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }
    public void setPrice(double price) { this.price = price; }
}

public class MovieTicketBookingSystem {
    public static void main(String[] args) {
        System.out.println("🎬 MOVIE TICKET BOOKING SYSTEM 🎬");
        System.out.println("==================================\n");
        
        // Creating tickets using different constructors
        
        // 1. Default constructor
        System.out.println("1. Ticket created with Default Constructor:");
        MovieTicket ticket1 = new MovieTicket();
        ticket1.printTicket();
        
        // 2. Constructor with movie name only
        System.out.println("2. Ticket created with Movie Name only:");
        MovieTicket ticket2 = new MovieTicket("Avengers: Endgame");
        ticket2.printTicket();
        
        // 3. Constructor with movie name and seat number
        System.out.println("3. Ticket created with Movie Name and Seat Number:");
        MovieTicket ticket3 = new MovieTicket("Spider-Man: No Way Home", 15);
        ticket3.printTicket();
        
        // 4. Full constructor with all details
        System.out.println("4. Ticket created with Full Constructor:");
        MovieTicket ticket4 = new MovieTicket("The Batman", "INOX", 25, 350.0);
        ticket4.printTicket();
        
        // 5. Another full constructor example
        System.out.println("5. Another Premium Ticket:");
        MovieTicket ticket5 = new MovieTicket("Top Gun: Maverick", "Cinepolis", 12, 450.0);
        ticket5.printTicket();
        
        // 6. Multiple tickets with same movie but different seats
        System.out.println("6. Group Booking - Same Movie, Different Seats:");
        MovieTicket[] groupTickets = {
            new MovieTicket("Doctor Strange", "PVR", 20, 200.0),
            new MovieTicket("Doctor Strange", "PVR", 21, 200.0),
            new MovieTicket("Doctor Strange", "PVR", 22, 200.0)
        };
        
        for (int i = 0; i < groupTickets.length; i++) {
            System.out.println("Group Ticket " + (i + 1) + ":");
            groupTickets[i].printTicket();
        }
        
        // Demonstrating constructor overloading concept
        System.out.println("📊 CONSTRUCTOR OVERLOADING DEMONSTRATION:");
        System.out.println("=========================================");
        System.out.println("✓ Default Constructor: MovieTicket()");
        System.out.println("✓ Single Parameter: MovieTicket(String movieName)");
        System.out.println("✓ Two Parameters: MovieTicket(String movieName, int seatNumber)");
        System.out.println("✓ Full Constructor: MovieTicket(String movieName, String theatreName, int seatNumber, double price)");
        System.out.println("\nAll constructors successfully demonstrated! 🎉");
    }
}