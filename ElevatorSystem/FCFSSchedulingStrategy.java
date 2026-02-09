package ElevatorSystem;

import java.util.Queue;

public class FCFSSchedulingStrategy implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        Direction direction = elevator.getDirection();
        int currentFloor = elevator.getCurrentFloor();

        Queue<ElevatorRequest> requestsQueue = elevator.getRequestsQueue();

        if(requestsQueue.isEmpty()) {
            return currentFloor;
        }

        int nextFloor = requestsQueue.poll().getFloor();

        if(direction == Direction.IDLE)
        {
            if(nextFloor > currentFloor) {
                elevator.setDirection(Direction.UP);
            } else if(nextFloor < currentFloor) {
                elevator.setDirection(Direction.DOWN);
            }
        }
        else if(direction == Direction.UP && nextFloor < currentFloor) {
            elevator.setDirection(Direction.DOWN);
        }
        else if(direction == Direction.DOWN && nextFloor > currentFloor) {
            elevator.setDirection(Direction.UP);
        }
        return nextFloor;
    }
}
