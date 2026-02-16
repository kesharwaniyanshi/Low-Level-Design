package VendingMachine;

public interface VendingMachineState {
     String getStateName();
    
    // Method to handle state transitions
    VendingMachineState next(VendingMachineContext context);
}
