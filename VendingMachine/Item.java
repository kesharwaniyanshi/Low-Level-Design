package VendingMachine;

public class Item {
    private ItemType itemType;
    private double price;

 public ItemType getType() {
        return itemType;
    }
    public void setType(ItemType type) {
        this.itemType = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
