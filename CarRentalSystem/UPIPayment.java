package CarRentalSystem;

public class UPIPayment implements PaymentStrategy {
    
    @Override
    public void processPayment(double amount) {
        // Implement UPI payment processing logic here
        System.out.println("Processing UPI payment of amount: $" + amount);
    }
    
}
