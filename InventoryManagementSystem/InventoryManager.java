package InventoryManagementSystem;

import java.util.List;

public class InventoryManager {
     private static InventoryManager instance;
    private List<Warehouse> warehouses;
    private ProductFactory productFactory;
    private List<InventoryObserver> observers;
    private ReplenishmentStrategy replenishmentStrategy;
}
