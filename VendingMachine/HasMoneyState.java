package VendingMachine;

public class HasMoneyState implements VendingMachineState{
    
    public HasMoneyState()
    {
System.out.println("Money inserted. Please make your selection.");
    }
@Override
public String getStateName() {
    return "Has Money State";
}
    public VendingMachineState next(VendingMachineContext context)
    {
       if (!context.getInventory().hasItems()) {
            return new OutOfStockState();
        }
        if (context.getCoinList().isEmpty()) {
            return new IdleState();
        }
        // Transition to SelectionState if user starts product selection
        if (context.getCurrentState() instanceof HasMoneyState) {
            return new SelectionState();
        }
        return this;
    }
    
}
