package ElevatorSystem;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Building building = new Building("ABC Towers", 10, 3); // 10 floors, 3 elevators
        ElevatorController controller = building.getElevatorController();

        // Create elevator display
        ElevatorDisplay display = new ElevatorDisplay();
        for (Elevator elevator : controller.getElevators()) {
            elevator.addObserver(display);
        }
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        // Display simulation details and options
        System.out.println("Elevator System Simulation");
        System.out.println("Building: " + building.getName());
        System.out.println("Floors: " + building.getNumberOfFloors());
        System.out.println("Elevators: " + controller.getElevators().size());

        while (running) {
            System.out.println("nSelect an option:");
            System.out.println("1. Request elevator (external)");
            System.out.println("2. Request floor (internal)");
            System.out.println("3. Simulate next step");
            System.out.println("4. Change scheduling strategy");
            System.out.println("5. Exit simulation");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Handle external elevator request
                    System.out.println("Enter elevator id");
                    int externalElevatorId = scanner.nextInt();
                    controller.setCurrentElevator(externalElevatorId);

                    System.out.println("Enter floor number");
                    int externalFloor = scanner.nextInt();
                    System.out.println("Enter direction 1 for UP , 2 for DOWN");
                    int direction = scanner.nextInt();
                    controller.requestElevator(externalElevatorId, externalFloor,
                            direction == 1 ? Direction.UP : Direction.DOWN);
                    break;
                case 2:
                    // Handle internal floor request
                    System.out.println("Enter elevator id");
                    int internalElevatorId = scanner.nextInt();
                    controller.setCurrentElevator(internalElevatorId);

                    System.out.println("Enter floor number");
                    int internalFloor = scanner.nextInt();
                    controller.requestFloor(internalElevatorId, internalFloor);
                    break;
                case 3:
                    // Simulate the next step in the system
                    System.out.println("Simulating next step...");
                    controller.step(); // Perform the simulation step
                    displayElevatorStatus(
                            controller.getElevators()); // Display elevator statuses
                    break;
                case 4:
                    // Change the scheduling strategy
                    System.out.println("Select strategy:");
                    System.out.println("1. SCAN Algorithm");
                    System.out.println("2. FCFS Algorithm");
                    System.out.println("3. Look Algorithm");
                    int strategyChoice = scanner.nextInt();
                    if (strategyChoice == 1) {
                        controller.setSchedulingStrategy(new ScanSchedulingStrategy());
                        System.out.println("Strategy set to SCAN Algorithm");
                    } else {
                        controller.setSchedulingStrategy(new FCFSSchedulingStrategy());
                        System.out.println("Strategy set to Nearest Elevator First");
                    }
                    break;
                case 5:
                    // Exit the simulation
                    running = false;
                    break;
                default:
                    // Handle invalid choices
                    System.out.println("Invalid choice!");
            }
        }
        scanner.close(); // Close the scanner to release resources
        System.out.println("Simulation ended");
    }

    // Display the status of all elevators in the system
    private static void displayElevatorStatus(List<Elevator> elevators) {
        System.out.println("nElevator Status:");
        for (Elevator elevator : elevators) {
            // Print details of each elevator, including current floor, direction, and
            // state
            System.out.println("Elevator " + elevator.getId() + ": Floor "
                    + elevator.getCurrentFloor() + ", Direction "
                    + elevator.getDirection() + ", State " + elevator.getState()
                    + ", Destinations " + elevator.getDestinationFloors());
        }
    }
}
