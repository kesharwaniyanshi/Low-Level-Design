package ElevatorSystem;

public class ElevatorRequest implements ElevatorCommand, Comparable<ElevatorRequest> {
    private int elevatorId; 
    private int floor;
    private Direction direction;
    private ElevatorController controller;
    private boolean isInternalRequest;
    
    public ElevatorRequest(int elevatorId, int floor, Direction direction, ElevatorController controller, boolean isInternalRequest) {
        this.elevatorId = elevatorId;
        this.floor = floor;
        this.direction = direction;
        this.controller = controller;
        this.isInternalRequest = isInternalRequest;
    }
    
    @Override
    public void execute() {
        if(isInternalRequest){
            controller.requestFloor(elevatorId, floor);
        }
        else {
            controller.requestElevator(elevatorId, floor, direction);
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public int getFloor() {
        return floor;
    }
    
    public boolean isInternalRequest() {
        return isInternalRequest;
    }
    
    @Override
    public int compareTo(ElevatorRequest other) {
        return Integer.compare(this.floor, other.floor);
    }
    
    @Override
    public String toString() {
        return "Floor " + floor + " (" + direction + ")";
    }
}
