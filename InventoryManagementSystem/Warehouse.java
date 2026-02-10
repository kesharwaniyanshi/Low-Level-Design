package InventoryManagementSystem;

import java.util.Map;

public class Warehouse {
    private int id;
    private String name;
    private String location;
    private Map<String, Product> products; // SKU -> Product

    public void addProduct(Product product, int quantity);

    public void removeProduct(String sku, int quantity);

    public int getAvailableQuantity(String sku);

}
