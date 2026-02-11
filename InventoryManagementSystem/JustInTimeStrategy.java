package InventoryManagementSystem;

public class JustInTimeStrategy implements ReplenishmentStrategy {
    public void replenish(Product product) {
    System.out.println("Implementing Just-In-Time inventory management strategy...for product: " + product.getName());
}
}