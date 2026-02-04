package ParkingLotSystem;

public class CreditCardPayment implements PaymentStrategy {
    private double amount;
    public CreditCardPayment(double fee) {
        this.amount = fee;
    }
    @Override
    public void processPayment(double amount) {
        // Logic to process credit card payment
        System.out.println("Processing credit card payment of amount: " + amount + " using credit card.");
    }
}
