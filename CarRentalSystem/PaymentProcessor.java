package CarRentalSystem;

public class PaymentProcessor {
    public boolean processPayment(PaymentStrategy paymentMethod, double amount) {
        // Simulate payment processing logic
        System.out.println("Processing payment of $" + amount + " using " + paymentMethod.getClass().getSimpleName());
        paymentMethod.processPayment(amount);

        // In a real implementation, integrate with a payment gateway here
        return true; // Assume payment is always successful for this example
    }
}
