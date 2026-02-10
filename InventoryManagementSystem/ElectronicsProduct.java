package InventoryManagementSystem;

public class ElectronicsProduct extends Product {
    private String brand;
    private int warranty;

    public ElectronicsProduct(String name, double price, int quantity, String sku, int threshold) {

        super();
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setSku(sku);
        setProductCategory(ProductCategory.ELECTRONICS);
        setThreshold(threshold);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }
}
