package InventoryManagementSystem;

import InventoryManagementSystem.Product;

public class ProductFactory {
    public Product createProduct(String name, double price, int quantity, String sku, ProductCategory category, int threshold) {
        switch (category) {
            case ELECTRONICS:
                return new ElectronicsProduct(name, price, quantity, sku, threshold);
            case CLOTHING:
                return new ClothingProducts(name, price, quantity, sku, threshold);
            case GROCERY:
                return new GroceryProduct(name, price, quantity, sku, threshold);
            default:
                throw new IllegalArgumentException("Invalid product category");
        }
    }
}
