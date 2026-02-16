package VendingMachine;

public class DispenseState implements VendingMachineState {

    public DispenseState() {
        System.out.println("Dispensing item...");
    }

    @Override
    public String getStateName() {
        return "Dispense State";
    }

    @Override
    public VendingMachineState next(VendingMachineContext vendingMachine) {
        return new IdleState();
    }

}
