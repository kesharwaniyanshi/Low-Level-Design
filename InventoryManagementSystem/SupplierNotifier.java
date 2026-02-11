package InventoryManagementSystem;

public class SupplierNotifier implements InventoryObserver {
    @Override
    public void update(Product product) {
        // Logic to notify suppliers about low stock or replenishment needs
        System.out.println("Notifying suppliers about product: " + product.getName());
    }
    
}
