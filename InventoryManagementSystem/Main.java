package InventoryManagementSystem;

public class Main {
    public static void main(String[] args) {
    System.out.println("=== Inventory Management Demo ===");

    // Get the singleton instance of InventoryManager
    InventoryManager inventoryManager = InventoryManager.getInstance();

    // Register observers
    inventoryManager.addObserver(new DashboardAlert());
    inventoryManager.addObserver(new SupplierNotifier());

    // Create and add warehouses
    Warehouse warehouse1 = new Warehouse("Warehouse 1");
    Warehouse warehouse2 = new Warehouse("Warehouse 2");
    inventoryManager.addWarehouse(warehouse1);
    inventoryManager.addWarehouse(warehouse2);

    // Create products using ProductFactory
    ProductFactory productFactory = new ProductFactory();
    Product laptop = productFactory.createProduct(
      "Laptop", 1000.0, 50, "SKU123", ProductCategory.ELECTRONICS, 25);
    Product tShirt = productFactory.createProduct(
      "T-Shirt", 20.0, 200, "SKU456", ProductCategory.CLOTHING, 10);
    Product apple = productFactory.createProduct(
      "Apple", 1.0, 100, "SKU789", ProductCategory.GROCERY, 30);

    // Add products to warehouses
    warehouse1.addProduct(laptop, 15);
    warehouse1.addProduct(tShirt, 20);
    warehouse2.addProduct(apple, 50);

    System.out.println("Initial stock levels:");
    System.out.println("- SKU123 (Laptop): " + warehouse1.getAvailableQuantity("SKU123"));
    System.out.println("- SKU456 (T-Shirt): " + warehouse1.getAvailableQuantity("SKU456"));
    System.out.println("- SKU789 (Apple): " + warehouse2.getAvailableQuantity("SKU789"));

    // Set replenishment strategy to Just-In-Time
    inventoryManager.setReplenishmentStrategy(new JustInTimeStrategy());
    System.out.println("\nRunning global inventory check (Just-In-Time)...");

    // Perform inventory check and replenish if needed
    inventoryManager.performInventoryCheck();

    // Switch replenishment strategy to Bulk Order
    inventoryManager.setReplenishmentStrategy(new BulkOrderStrategy());
    System.out.println("\nReplenish a specific product (Bulk Order)...");

    // Replenish a specific product if needed
    inventoryManager.checkAndReplenish("SKU123");
  }
}
