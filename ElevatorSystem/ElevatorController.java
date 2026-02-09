package ElevatorSystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private int currentElevatorId;
    private List<Elevator> elevators;
    private List<Floor> floors;
    private SchedulingStrategy schedulingStrategy;

    public ElevatorController(int numberOfElevators, int numberOfFloors) {
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
        this.schedulingStrategy = new ScanSchedulingStrategy();
        for (int i = 1; i <= numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }
        // Initialize floors
        for (int i = 1; i <= numberOfFloors; i++) {
            floors.add(new Floor(i));
        }
    }

    public void setSchedulingStrategy(SchedulingStrategy strategy) {
        this.schedulingStrategy = strategy;
    }

    // Handle external elevator requests from a specific floor
    public void requestElevator(int elevatorId, int floor, Direction direction) {
        System.out.println("External request received for floor " + floor + " in direction " + direction);

        Elevator elevator = getElevatorById(elevatorId);

        if (elevator != null) {
            ElevatorRequest request = new ElevatorRequest(elevatorId, floor, direction, this, false);
            elevator.addRequest(request);
            System.out.println("Assigned elevator " + elevator.getId()
                    + " to floor " + floor);
        } else {
            // If no suitable elevator is found
            System.out.println("No elevator available for floor " + floor);
        }
    }

    // Handle internal elevator requests to a specific floor
    public void requestFloor(int elevatorId, int floorNumber) {
        // Find the elevator by its ID
        Elevator elevator = getElevatorById(elevatorId);
        System.out.println("Internal request: Elevator " + elevator.getId()
                + " to floor " + floorNumber);
        // Determine the direction of the request
        Direction direction = floorNumber > elevator.getCurrentFloor()
                ? Direction.UP
                : Direction.DOWN;
        // Add the request to the elevator
        elevator.addRequest(
                new ElevatorRequest(elevatorId, floorNumber, direction, this, true));
    }

    // Find an elevator by its ID
    private Elevator getElevatorById(int elevatorId) {
        for (Elevator elevator : elevators) {
            if (elevator.getId() == elevatorId)
                return elevator;
        }
        return null; // Return null if no matching elevator is found
    }

    // Perform a simulation step by moving all elevators
    public void step() {
        // Iterate through all elevators
        for (Elevator elevator : elevators) {
            // Only process elevators with pending requests
            if (!elevator.getRequestsQueue().isEmpty()) {
                // Use the scheduling strategy to find the next stop
                int nextStop = schedulingStrategy.getNextStop(elevator);

                // Move the elevator to the next stop if needed
                if (elevator.getCurrentFloor() != nextStop)
                    elevator.moveToNextStop(nextStop);
            }
        }
    }

    // Get the list of all elevators
    public List<Elevator> getElevators() {
        return elevators;
    }

    // Get the list of all floors
    public List<Floor> getFloors() {
        return floors;
    }

    // Set the ID of the current elevator
    public void setCurrentElevator(int elevatorId) {
        this.currentElevatorId = elevatorId;
    }
}
