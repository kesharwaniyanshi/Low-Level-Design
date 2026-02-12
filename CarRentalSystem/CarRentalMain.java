package CarRentalSystem;

import java.sql.Date;
import java.util.Scanner;

public class CarRentalMain {
    public static void main(String[] args) {
        RentalSystem rentalSystem = RentalSystem.getInstance();

        RentalStore rentalStore1 = new RentalStore(1, "Downtown Rental",
                new Location("123 Main St", "Cityville", "State", "12345"));
        RentalStore rentalStore2 = new RentalStore(2, "Airport Rental",
                new Location("456 Airport Rd", "Cityville", "State", "12345"));

        rentalSystem.addRentalStore(rentalStore1);
        rentalSystem.addRentalStore(rentalStore2);

        // Create vehicles using Factory Pattern
        Vehicle economyCar = VehicleFactory.createVehicle(VehicleType.ECONOMY, "EC001", "Toyota", 50.0);
        Vehicle suvCar = VehicleFactory.createVehicle(VehicleType.SUV, "SV001", "Honda", 75.0);

        // Add vehicles to stores
        rentalStore1.addVehicle(economyCar);
        rentalStore2.addVehicle(suvCar);

        ReservationManager reservationManager = rentalSystem.getReservationManager();

        User user1 = new User(1, "John Doe", "s@gmail.com");
        User user2 = new User(2, "Jane Smith", "jane.smith@gmail.com");

        rentalSystem.registerUser(user1);
        rentalSystem.registerUser(user2);

        Reservation reservation1 = rentalSystem.createReservation(user1.getId(), "EC001", rentalStore1.getId(),
                rentalStore2.getId(), new Date(2025 - 1900, 4, 1),
                new Date(2025 - 1900, 5, 15));

        Scanner scanner = new Scanner(System.in);
        System.out.println("nProcessing payment for reservation #" + reservation1.getId());
        System.out.println("Total amount: $" + reservation1.getTotalCost());
        System.out.println("Select payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. UPI");

        int paymentChoice = scanner.nextInt();

        PaymentStrategy paymentMethod;
        switch (paymentChoice) {
            case 1:
                paymentMethod = new CreditCardPayment();
                break;
            case 2:
                paymentMethod = new UPIPayment();
                break;
            default:
                System.out.println("Invalid choice! Defaulting to credit card payment.");
                paymentMethod = new CreditCardPayment();
                break;
        }
        boolean paymentSuccess = rentalSystem.processPayment(reservation1.getId(), paymentMethod);
        if (paymentSuccess) {
            System.out.println("Payment successful!");

            // Start the rental
            reservationManager.startRental(reservation1.getId());

            // Simulate rental period
            System.out.println("nSimulating rental period...");

            // Complete the rental
            reservationManager.completeRental(reservation1.getId());
        } else {
            System.out.println("Payment failed!");
        }

    }
}
