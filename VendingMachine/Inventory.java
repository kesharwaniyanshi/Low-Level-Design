package VendingMachine;

public class Inventory {
    private ItemShelf[] inventory;

    public Inventory(int itemCount) {
        inventory = new ItemShelf[itemCount];
        initialEmptyInventory();
    }

    public ItemShelf[] getInventory() {
        return inventory;
    }

    private void initialEmptyInventory() {
        int startCode = 101;
        for (int i = 0; i < inventory.length; i++) {
            ItemShelf itemShelf = new ItemShelf(startCode);
            inventory[i] = itemShelf;
            startCode++;
        }
    }

    public void addItem(Item item, int code) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == code) {
                // Add the item to the shelf
                itemShelf.addItem(item);
                return;
            }
        }
        throw new Exception("Invalid Code");
    }

    public Item getItem(int code) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == code) {
                if (itemShelf.checkIsSoldOut()) {
                    throw new Exception("Item is sold out");
                } else {
                    Item item = itemShelf.getItems().get(0); // Get the first item from the shelf
                    return item; // Return the item to the caller
                }
            }
        }
        throw new Exception("Invalid Code");
    }

    public void updateSoldOutItem(int code) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == code) {
               if(itemShelf.getItems().isEmpty()) {
                   itemShelf.setIsSoldOut(true);
               } 
               else {
                   itemShelf.setIsSoldOut(false);
               }
               return;
            }
        }
        throw new Exception("Invalid Code");
    }

     public void removeItem(int codeNumber) throws Exception {
    for (ItemShelf itemShelf : inventory) {
      if (itemShelf.getCode() == codeNumber) {
        itemShelf.removeItem(
            itemShelf.getItems().get(0)); // Remove the specific item from the shelf
        return;
      }
    }
    throw new Exception("Invalid Code");
  }

   public boolean hasItems() { 
	        for(ItemShelf itemShelf : inventory){ 
	            if(!itemShelf.checkIsSoldOut()) return true; 
	        } 
	        return false;  
	   }  

}
