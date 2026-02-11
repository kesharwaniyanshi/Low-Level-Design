package InventoryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
  private static InventoryManager instance;
  private List<Warehouse> warehouses;
  private ProductFactory productFactory;
  private List<InventoryObserver> observers;
  private ReplenishmentStrategy replenishmentStrategy;

  private InventoryManager() {
    // Initialize warehouses, productFactory, observers, and default replenishment
    // strategy
    warehouses = new ArrayList<>();
    productFactory = new ProductFactory();
  }

  public static synchronized InventoryManager getInstance() {
    if (instance == null) {
      instance = new InventoryManager();
    }
    return instance;
  }

  // Observer pattern methods
  public void addObserver(InventoryObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(InventoryObserver observer) {
    observers.remove(observer);
  }

  // Strategy pattern method
  public void setReplenishmentStrategy(ReplenishmentStrategy strategy) {
    this.replenishmentStrategy = strategy;
  }

  // Warehouse management
  public void addWarehouse(Warehouse warehouse) {
    warehouses.add(warehouse);
  }

  public void removeWarehouse(Warehouse warehouse) {
    warehouses.remove(warehouse);
  }

  // Product inventory operations
  public Product getProductBySku(String sku) {
    for (Warehouse warehouse : warehouses) {
      Product product = warehouse.getProductBySku(sku);
      if (product != null) {
        return product;
      }
    }
    return null;
  }

  // Check stock levels and apply replenishment strategy if needed
  public void checkAndReplenish(String sku) {
    Product product = getProductBySku(sku);
    if (product != null) {
      // If product is below threshold, notify observers
      if (product.getQuantity() < product.getThreshold()) {
        notifyObservers(product);
        // Apply current replenishment strategy
        if (replenishmentStrategy != null) {
          replenishmentStrategy.replenish(product);
        }
      }
    }
  }

  // Global inventory check
  public void performInventoryCheck() {
    for (Warehouse warehouse : warehouses) {
      for (Product product : warehouse.getAllProducts()) {
        if (product.getQuantity() < product.getThreshold()) {
          notifyObservers(product);
          if (replenishmentStrategy != null) {
            replenishmentStrategy.replenish(product);
          }
        }
      }
    }
  }

  private void notifyObservers(Product product) {
    for (InventoryObserver observer : observers) {
      observer.update(product);
    }
  }
}
