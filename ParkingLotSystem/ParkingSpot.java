package ParkingLotSystem;

public abstract class ParkingSpot {
    private int spotNumber;
    private boolean isOccupied;
    private Vehicle vehicle;
    private String spotType;

    public ParkingSpot(int spotNumber, String spotType) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    public abstract boolean canParkVehicle(Vehicle vehicle);

    public void parkVehicle(Vehicle vehicle) {
        if (isOccupied)
            throw new IllegalStateException("Parking Spot is already occupied");
        if (!canParkVehicle(vehicle))
            throw new IllegalArgumentException(
                    "Vehicle type " + vehicle.getVehicleType() + " cannot be parked in spot type " + spotType);
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void vacate() {
        // Check if the spot is already vacant
        if (!isOccupied) {
            throw new IllegalStateException("Spot is already vacant.");
        }
        this.vehicle = null;
        this.isOccupied = false;
    }

    // Getter for spot number
    public int getSpotNumber() {
        return spotNumber;
    }

    // Getter for the vehicle parked in the spot
    public Vehicle getVehicle() {
        return vehicle;
    }

    // Getter for spot type
    public String getSpotType() {
        return spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
