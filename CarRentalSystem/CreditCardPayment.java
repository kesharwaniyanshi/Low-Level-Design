package CarRentalSystem;

public class CreditCardPayment implements PaymentStrategy {
   
    @Override
    public void processPayment(double amount) {
        // Implement credit card payment processing logic here
        System.out.println("Processing credit card payment of amount: $" + amount);
    }
}
