package ParkingLotSystem;

public class CarVehicle extends Vehicle {
    
    public CarVehicle(String licensePlate, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, "car", feeStrategy);
    }
}
