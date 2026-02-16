package VendingMachine;

public class IdleState implements VendingMachineState {

    public IdleState() {
        System.out.println("Vending Machine is idle.");
    }

    @Override
    public String getStateName() {
        return "Idle State";
    }

    public VendingMachineState next(VendingMachineContext context) {
        if (!context.getInventory().hasItems())
            return new OutOfStockState();
        if (!context.getCoinList().isEmpty()) {
            return new HasMoneyState();
        }
        // Otherwise, remain in idle state
        return this;
    }
}
