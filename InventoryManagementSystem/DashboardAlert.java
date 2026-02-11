package InventoryManagementSystem;

public class DashboardAlert implements InventoryObserver {
    @Override
    public void update(Product product) {
        // Logic to display alerts on the dashboard about low stock or other inventory issues
        System.out.println("Dashboard Alert: Product " + product.getName() + " has low stock.");
    }
    
}
