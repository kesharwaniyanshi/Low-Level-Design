package VendingMachine;

import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        VendingMachineContext vendingMachine = new VendingMachineContext();
        try {
            System.out.println("|");
            System.out.println("Filling up the inventory");
            System.out.println("|");
            fillUpInventory(vendingMachine);
            displayInventory(vendingMachine);

            System.out.println("|");
            System.out.println("Inserting coins");
            System.out.println("|");

            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.print("Insert coin (1, 2, 5, 10) or 0 to finish: ");
                    int coinValue = scanner.nextInt();
                    if (coinValue == 0) {
                        break;
                    }
                    Coin coin = mapCoin(coinValue);
                    if (coin == null) {
                        System.out.println("Invalid coin. Try again.");
                        continue;
                    }
                    vendingMachine.clickOnInsertCoinButton(coin);
                }

                System.out.println("|");
                System.out.println("Clicking on ProductSelectionButton");
                System.out.println("|");
                System.out.print("Enter item code to dispense: ");
                int code = scanner.nextInt();
                vendingMachine.clickOnStartProductSelectionButton(code);
            }

            displayInventory(vendingMachine);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            displayInventory(vendingMachine);
        }
    }

    // Method to fill up the inventory of the vending machine
    private static void fillUpInventory(VendingMachineContext vendingMachine) {
        for (int i = 0; i < 10; i++) {
            Item newItem = new Item();
            int codeNumber = 101 + i; // Shelf code
            // Set item type and price based on the index range
            if (i >= 0 && i < 3) {
                newItem.setType(ItemType.COKE);
                newItem.setPrice(12);
            } else if (i >= 3 && i < 5) {
                newItem.setType(ItemType.PEPSI);
                newItem.setPrice(9);
            } else if (i >= 5 && i < 7) {
                newItem.setType(ItemType.JUICE);
                newItem.setPrice(13);
            } else if (i >= 7 && i < 10) {
                newItem.setType(ItemType.SODA);
                newItem.setPrice(7);
            }
            // Update the inventory with multiple same items per shelf
            for (int j = 0; j < 5; j++) {
                // Add 5 items to each shelf
                vendingMachine.updateInventory(newItem, codeNumber);
            }
        }
    }

    // Method to display the current inventory of the vending machine
    private static void displayInventory(VendingMachineContext vendingMachine) {
        ItemShelf[] slots = vendingMachine.getInventory().getInventory();
        for (ItemShelf slot : slots) {
            List<Item> items = slot.getItems(); // Get the list of items in the shelf
            if (!items.isEmpty()) {
                System.out.println("CodeNumber: " + slot.getCode() + " Items: ");
                for (Item item : items) { // Display all items in the shelf
                    System.out.println(
                            "    - Item: " + item.getType().name() + ", Price: " + item.getPrice());
                }
                System.out.println("SoldOut: " + slot.checkIsSoldOut());
            } else {
                // Display empty shelf information
                System.out.println("CodeNumber: " + slot.getCode() + " Items: EMPTY"
                        + " SoldOut: " + slot.checkIsSoldOut());
            }
        }
    }

    private static Coin mapCoin(int value) {
        switch (value) {
            case 1:
                return Coin.ONE_RUPEE;
            case 2:
                return Coin.TWO_RUPEES;
            case 5:
                return Coin.FIVE_RUPEES;
            case 10:
                return Coin.TEN_RUPEES;
            default:
                return null;
        }
    }

}
