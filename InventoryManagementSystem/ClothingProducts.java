package InventoryManagementSystem;


public class ClothingProducts extends Product {
    private String size;
    private String color;


    public ClothingProducts(String name, double price, int quantity, String sku, int threshold) {
        super();
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setSku(sku);
        setProductCategory(ProductCategory.CLOTHING);
        setThreshold(threshold);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public String getColor()
    {
        return color;
    }
    public void setColor(String color)
    {
        this.color = color;
    }
}
