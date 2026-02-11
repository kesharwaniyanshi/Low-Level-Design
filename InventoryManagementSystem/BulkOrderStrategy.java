package InventoryManagementSystem;

public class BulkOrderStrategy implements ReplenishmentStrategy {
    public void replenish(Product product) {
        System.out.println("Processing bulk order for product: " + product.getName());
    }
}
