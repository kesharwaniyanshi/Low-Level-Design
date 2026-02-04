package ParkingLotSystem;

public class UPIPayment implements PaymentStrategy {
    private double amount;
    public UPIPayment(double fee) {
        this.amount = fee;
    }
    @Override
    public void processPayment(double amount) {
        // Logic to process UPI payment
        System.out.println("Processing UPI payment of amount: " + amount + " using UPI.");
    }
    
}
