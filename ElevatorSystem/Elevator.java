package ElevatorSystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Elevator {
    private int id;
    private int currentFloor;
    private ElevatorState state;
    private Direction direction;
    private Queue<ElevatorRequest> requests;
    private List<ElevatorObserver> observers;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.direction = Direction.IDLE;
        this.requests = new LinkedList<>();
        this.observers = new ArrayList<>();
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ElevatorObserver observer) {
        observers.remove(observer);
    }

    public void setState(ElevatorState newState) {
        this.state = newState;
        notifyStateChange(newState);
    }

    public void notifyStateChange(ElevatorState state) {
        for (ElevatorObserver observer : observers) {
            observer.onElevatorStateChange(this, state);
        }
    }

    public void notifyFloorChange(int currentFloor) {
        for (ElevatorObserver observer : observers) {
            observer.onElevatorFloorChange(this, currentFloor);
        }
    }

    public void addRequest(ElevatorRequest request) {
        if (!requests.contains(request)) {
            requests.add(request);
        }
        int requestedFloor = request.getFloor();
        // If elevator is idle, determine direction and start moving
        if (state == ElevatorState.IDLE && !requests.isEmpty()) {
            if (requestedFloor > currentFloor) {
                direction = Direction.UP;
            } else if (requestedFloor < currentFloor) {
                direction = Direction.DOWN;
            }
            setState(ElevatorState.MOVING);
        }
    }

    public void moveToNextStop(int nextStop) {
        if (state != ElevatorState.MOVING)
            return;
        while (currentFloor != nextStop) {
            // Update floor based on direction
            if (direction == Direction.UP) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            // Notify observers about the floor change
            notifyFloorChange(currentFloor);
            // Complete arrival once the target floor is reached
            if (currentFloor == nextStop) {
                completeArrival();
                return;
            }
        }
    }

    public void completeArrival() {
        setState(ElevatorState.STOPPED);

        requests.removeIf(request -> request.getFloor() == currentFloor);

        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            setState(ElevatorState.IDLE);
        } else {
            // Otherwise, continue moving after a brief stop
            setState(ElevatorState.MOVING);
        }
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public ElevatorState getState() {
        return state;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Queue<ElevatorRequest> getRequestsQueue() {
        return new LinkedList<>(requests);
    }

    public List<ElevatorRequest> getDestinationFloors() {
        return new ArrayList<>(requests);
    }

}
