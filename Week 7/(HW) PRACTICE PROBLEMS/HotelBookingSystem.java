public class HotelBookingSystem {
    
    private static final double SINGLE_ROOM_RATE = 100.0;
    private static final double DOUBLE_ROOM_RATE = 150.0;
    private static final double SUITE_ROOM_RATE = 300.0;
    private static final double MEAL_PACKAGE_RATE = 45.0;
    private static final double DECORATION_BASE_FEE = 200.0;
    private static final double CATERING_PER_GUEST = 75.0;
    
    private double getRoomRate(String roomType) {
        if (roomType.equals("Single")) {
            return SINGLE_ROOM_RATE;
        } else if (roomType.equals("Double")) {
            return DOUBLE_ROOM_RATE;
        } else if (roomType.equals("Suite")) {
            return SUITE_ROOM_RATE;
        }
        return SINGLE_ROOM_RATE;
    }
    
    public double calculateBookingPrice(String roomType, int nights) {
        double roomRate = getRoomRate(roomType);
        double totalCost = roomRate * nights;
        
        System.out.println("Standard Booking");
        System.out.println("Room type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Room rate: $" + roomRate + " per night");
        System.out.println("Total cost: $" + totalCost);
        
        return totalCost;
    }
    
    public double calculateBookingPrice(String roomType, int nights, double seasonalMultiplier) {
        double roomRate = getRoomRate(roomType);
        double baseCost = roomRate * nights;
        double totalCost = baseCost * seasonalMultiplier;
        double seasonalAdjustment = totalCost - baseCost;
        
        System.out.println("Seasonal Booking");
        System.out.println("Room type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Base room rate: $" + roomRate + " per night");
        System.out.println("Base cost: $" + baseCost);
        System.out.println("Seasonal multiplier: " + seasonalMultiplier + "x");
        
        if (seasonalAdjustment > 0) {
            System.out.println("Seasonal surcharge: $" + seasonalAdjustment);
        } else {
            System.out.println("Seasonal discount: $" + Math.abs(seasonalAdjustment));
        }
        
        System.out.println("Total cost: $" + totalCost);
        
        return totalCost;
    }
    
    public double calculateBookingPrice(String roomType, int nights, double corporateDiscount, boolean includeMealPackage) {
        double roomRate = getRoomRate(roomType);
        double baseCost = roomRate * nights;
        double discountAmount = baseCost * (corporateDiscount / 100);
        double roomCostAfterDiscount = baseCost - discountAmount;
        double mealCost = 0.0;
        
        if (includeMealPackage) {
            mealCost = MEAL_PACKAGE_RATE * nights;
        }
        
        double totalCost = roomCostAfterDiscount + mealCost;
        
        System.out.println("Corporate Booking");
        System.out.println("Room type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Base room rate: $" + roomRate + " per night");
        System.out.println("Base cost: $" + baseCost);
        System.out.println("Corporate discount: " + corporateDiscount + "%");
        System.out.println("Discount savings: $" + discountAmount);
        System.out.println("Room cost after discount: $" + roomCostAfterDiscount);
        
        if (includeMealPackage) {
            System.out.println("Meal package: $" + MEAL_PACKAGE_RATE + " per night");
            System.out.println("Total meal cost: $" + mealCost);
        } else {
            System.out.println("No meal package selected");
        }
        
        System.out.println("Total cost: $" + totalCost);
        
        return totalCost;
    }
    
    public double calculateBookingPrice(String roomType, int nights, int guestCount, boolean includeDecorations, String cateringOption) {
        double roomRate = getRoomRate(roomType);
        double baseCost = roomRate * nights;
        double decorationCost = 0.0;
        double cateringCost = 0.0;
        
        if (includeDecorations) {
            decorationCost = DECORATION_BASE_FEE;
        }
        
        if (cateringOption.equals("Standard")) {
            cateringCost = guestCount * CATERING_PER_GUEST;
        } else if (cateringOption.equals("Premium")) {
            cateringCost = guestCount * CATERING_PER_GUEST * 1.5;
        } else if (cateringOption.equals("Luxury")) {
            cateringCost = guestCount * CATERING_PER_GUEST * 2.0;
        }
        
        double totalCost = baseCost + decorationCost + cateringCost;
        
        System.out.println("Wedding Package Booking");
        System.out.println("Room type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Guest count: " + guestCount);
        System.out.println("Base room rate: $" + roomRate + " per night");
        System.out.println("Room cost: $" + baseCost);
        
        if (includeDecorations) {
            System.out.println("Decoration package: $" + decorationCost);
        } else {
            System.out.println("No decorations selected");
        }
        
        if (!cateringOption.equals("None")) {
            System.out.println("Catering option: " + cateringOption);
            System.out.println("Catering cost: $" + cateringCost);
        } else {
            System.out.println("No catering selected");
        }
        
        System.out.println("Total wedding package cost: $" + totalCost);
        
        return totalCost;
    }
    
    public static void main(String[] args) {
        HotelBookingSystem hotel = new HotelBookingSystem();
        
        System.out.println("Hotel Booking System");
        System.out.println();
        
        hotel.calculateBookingPrice("Double", 3);
        System.out.println();
        
        hotel.calculateBookingPrice("Suite", 2, 1.3);
        System.out.println();
        
        hotel.calculateBookingPrice("Double", 4, 15.0, true);
        System.out.println();
        
        hotel.calculateBookingPrice("Suite", 2, 50, true, "Premium");
        System.out.println();
        
        hotel.calculateBookingPrice("Single", 1, 20, false, "Standard");
        System.out.println();
        
        System.out.println("Method Overloading allows multiple booking options:");
        System.out.println("Same method name with different parameter combinations");
        System.out.println("Java automatically selects the correct booking type");
    }
}