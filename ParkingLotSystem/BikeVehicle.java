package ParkingLotSystem;

public class BikeVehicle extends Vehicle{
    public BikeVehicle(String licensePlate, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, "bike", feeStrategy);
    }
}
