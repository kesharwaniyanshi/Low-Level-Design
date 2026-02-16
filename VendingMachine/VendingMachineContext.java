package VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineContext {
  private VendingMachineState currentState;
  private Inventory inventory; // Inventory to store items
  private List<Coin> coinList; // List to keep track of inserted coins
  private int selectedItemCode; // Code of the selected item

  public VendingMachineContext() {
    this.currentState = new IdleState(); // Initial state of the vending machine
    this.inventory = new Inventory(10); // Initialize inventory with 10 item shelves
    coinList = new ArrayList<>(); // Initialize the list to store inserted coins
    System.out.println("Vending Machine is in Idle State");
  }

  public VendingMachineState getCurrentState() {
    return currentState;
  }

  public void advanceState() {
    VendingMachineState nextState = currentState.next(this);
    currentState = nextState;
    System.out.println("Vending Machine is now in " + currentState.getStateName() + " State");
  }

  // Handles the insertion of a coin
  public void clickOnInsertCoinButton(Coin coin) {
    if (currentState instanceof IdleState || currentState instanceof HasMoneyState) {
      System.out.println("Inserted " + coin.name() + " worth " + coin.value);
      coinList.add(coin); // Add the coin to the list
      if (currentState instanceof IdleState) {
        advanceState(); // Move to HasMoney after first coin
      }
    } else {
      System.out.println("Cannot insert coin in " + currentState.getStateName());
    }
  }

  public void clickOnStartProductSelectionButton(int code) {
    if (currentState instanceof HasMoneyState) {
      System.out.println("Selected item with code: " + code);
      advanceState(); // Move to Selection state
      selectProduct(code); // Select the product
    } else {
      System.out.println("Cannot select product in " + currentState.getStateName());
    }
  }

  public void selectProduct(int codeNumber) {
    if (currentState instanceof SelectionState) {
      try {
        Item item = inventory.getItem(codeNumber); // Fetch the item from inventory

        int balance = getBalance(); // Calculate the total balance
        if (balance < item.getPrice()) { // Check for sufficient funds
          System.out.println(
              "Insufficient amount. Product price: " + item.getPrice() + ", paid: " + balance);
          resetSelection();
          currentState = new HasMoneyState();
          System.out.println("Vending Machine is now in " + currentState.getStateName() + " State");
          return;
        }
        setSelectedItemCode(codeNumber); // Set the selected item code
        advanceState(); // Move to dispense state
        dispenseItem(codeNumber); // Dispense the item

        if (balance >= item.getPrice()) { // Return change if applicable
          double change = balance - item.getPrice();
          System.out.println("Returning change: " + change);
        }
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    } else {
      System.out.println("Products can only be selected in Selection state");
    }
  }

  public void dispenseItem(int codeNumber) {
    if (currentState instanceof DispenseState) {
      try {
      Item item = inventory.getItem(codeNumber);
        System.out.println("Dispensing: " + item.getType());
        inventory.removeItem(codeNumber); // Remove item from inventory
        // Update inventory
        inventory.updateSoldOutItem(codeNumber);
        // Reset machine state
        resetBalance();
        resetSelection();
        advanceState(); // Move to the next state
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    } else {
      System.out.println("Items can only be dispensed in Dispense state");
    }
  }

  // Updates the inventory by adding a new item
  public void updateInventory(Item item, int codeNumber) {
    if (currentState instanceof IdleState) { // Only update inventory in Idle state
      try {
        inventory.addItem(item, codeNumber); // Add the item to inventory
        System.out.println("Added " + item.getType() + " to slot " + codeNumber);
      } catch (Exception e) {
        System.out.println("Error updating inventory: " + e.getMessage());
      }
    } else {
      System.out.println("Inventory can only be updated in Idle state");
    }
  }
  

   // Getters and setters for context properties
  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  public List<Coin> getCoinList() {
    return coinList;
  }

  public void setCoinList(List<Coin> coinList) {
    this.coinList = coinList;
  }

  public int getSelectedItemCode() {
    return selectedItemCode;
  }

  public void setSelectedItemCode(int codeNumber) {
    this.selectedItemCode = codeNumber;
  }

  // Resets the product selection
  public void resetSelection() {
    this.selectedItemCode = 0;
  }
   // Resets the balance by clearing all coins
  public void resetBalance() {
    coinList.clear();
  }

  private int getBalance() {
    int total = 0;
    for (Coin coin : coinList) {
      total += coin.value;
    }
    return total;
  }
}
