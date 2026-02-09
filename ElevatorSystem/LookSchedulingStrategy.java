package ElevatorSystem;

import java.util.Queue;

public class LookSchedulingStrategy implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        int currentFloor = elevator.getCurrentFloor();
        Queue<ElevatorRequest> requestsQueue = elevator.getRequestsQueue();

        if (requestsQueue == null || requestsQueue.isEmpty()) {
            return currentFloor;
        }

        ElevatorRequest primaryRequest = requestsQueue.peek();
        Direction travelDirection;
        int primaryFloor = primaryRequest.getFloor();
        if (primaryFloor > currentFloor) {
            travelDirection = Direction.UP;
        } else if (primaryFloor < currentFloor) {
            travelDirection = Direction.DOWN;
        } else {
            return currentFloor; // Already at the requested floor
        }

        Integer candidate = null;

        for (ElevatorRequest request : requestsQueue) {
            int requestFloor = request.getFloor();
            if (travelDirection == Direction.UP && requestFloor >= currentFloor && requestFloor <= primaryFloor) {
                if (request.isInternalRequest()
                        || (!request.isInternalRequest() && request.getDirection() == Direction.UP)) {
                    if (candidate == null || requestFloor < candidate) {
                        candidate = requestFloor;
                    }
                }
            } else if (travelDirection == Direction.DOWN && requestFloor <= currentFloor
                    && requestFloor >= primaryFloor) {
                if (request.isInternalRequest()
                        || (!request.isInternalRequest() && request.getDirection() == Direction.DOWN)) {
                    if (candidate == null || requestFloor > candidate) {
                        candidate = requestFloor;
                    }
                }
            }
        }

        return candidate != null ? candidate : primaryFloor;

    }
}
