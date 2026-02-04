package ParkingLotSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLotMain {

    public static void main(String[] args) {

        List<ParkingSpot> spots = new ArrayList<>();
        spots.add(new BikeParkingSpot(1));
        spots.add(new CarParkingSpot(2));
        spots.add(new CarParkingSpot(3));
        spots.add(new BikeParkingSpot(4));
        spots.add(new CarParkingSpot(5));

        ParkingLot parkingLot = new ParkingLot(spots);

        ParkingFeeStrategy basicFeeStrategy = new BasicFeeStrategy();

        Vehicle bike1 = VehicleFactory.createVehicle("bike", "BIKE123", basicFeeStrategy);
        Vehicle car1 = VehicleFactory.createVehicle("car", "CAR123", basicFeeStrategy);

        ParkingSpot carSpot = parkingLot.parkVehicle(car1);
        ParkingSpot bikeSpot = parkingLot.parkVehicle(bike1);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose your payment strategy: 1. Credit Card 2. UPI");

        int paymentMethod = scanner.nextInt();

        // Process payments using Strategy Patterns
        if (carSpot != null) {
            // Calculate fee using the specific strategy for the vehicle
            double carFee = car1.calculateFee(2, DurationType.HOURS);
            PaymentStrategy carPaymentStrategy = getPaymentStrategy(paymentMethod, carFee);
            carPaymentStrategy.processPayment(carFee);
            parkingLot.vacateSpot(carSpot, car1);
        }
        if (bikeSpot != null) {
            // Calculate fee using the specific strategy for the vehicle
            double bikeFee = bike1.calculateFee(3, DurationType.HOURS);
            PaymentStrategy bikePaymentStrategy = getPaymentStrategy(paymentMethod, bikeFee);
            bikePaymentStrategy.processPayment(bikeFee);
            parkingLot.vacateSpot(bikeSpot, bike1);
        }
        scanner.close();
    }

    private static PaymentStrategy getPaymentStrategy(
            int paymentMethod, double fee) {
        switch (paymentMethod) {
            case 1:
                return new CreditCardPayment(fee);
            case 2:
                return new UPIPayment(fee);
            default:
                System.out.println("Invalid choice! Default to Credit card payment.");
                return new CreditCardPayment(fee);
        }
    }
}
