package ElevatorSystem;

import java.util.PriorityQueue;
import java.util.Queue;

public class ScanSchedulingStrategy implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        Direction direction = elevator.getDirection();
        int currentFloor = elevator.getCurrentFloor();
        Queue<ElevatorRequest> requestsQueue = elevator.getRequestsQueue();

        if (requestsQueue.isEmpty()) {
            return currentFloor;
        }

        PriorityQueue<ElevatorRequest> upQueue = new PriorityQueue<>();
        PriorityQueue<ElevatorRequest> downQueue = new PriorityQueue<>((a, b) -> b.getFloor() - a.getFloor());

        while (!requestsQueue.isEmpty()) {
            ElevatorRequest request = requestsQueue.poll();
            if (request.getFloor() > currentFloor) {
                upQueue.offer(request);
            } else if (request.getFloor() < currentFloor) {
                downQueue.offer(request);
            }
        }

        int nextFloor = currentFloor;

        if (direction == Direction.UP) { // Handle movement in UP direction
            if (!upQueue.isEmpty()) {
                nextFloor = upQueue.poll().getFloor();
            } else if (!downQueue.isEmpty()) {
                elevator.setDirection(Direction.DOWN);
                nextFloor = downQueue.poll().getFloor();
            }
        } else if (direction == Direction.DOWN) { // Handle movement in DOWN direction
            if (!downQueue.isEmpty()) {
                nextFloor = downQueue.poll().getFloor();
            } else if (!upQueue.isEmpty()) {
                elevator.setDirection(Direction.UP);
                nextFloor = upQueue.poll().getFloor();
            }
        } else if (direction == Direction.IDLE) { // Handle IDLE state by choosing the nearest request
            int nearestUpwardRequest = upQueue.isEmpty() ? -1 : upQueue.peek().getFloor();
            int nearestDownwardRequest = downQueue.isEmpty() ? -1 : downQueue.peek().getFloor();
            if (nearestDownwardRequest == -1) {
                elevator.setDirection(Direction.UP);
                nextFloor = nearestUpwardRequest;
            } else if (nearestUpwardRequest == -1) {
                elevator.setDirection(Direction.DOWN);
                nextFloor = nearestDownwardRequest;
            } else {
                if (Math.abs(nearestUpwardRequest - currentFloor) < Math.abs(nearestDownwardRequest - currentFloor)) {
                    elevator.setDirection(Direction.UP);
                    nextFloor = nearestUpwardRequest;
                } else {
                    elevator.setDirection(Direction.DOWN);
                    nextFloor = nearestDownwardRequest;
                }
            }

        }
        return nextFloor;
    }
}
