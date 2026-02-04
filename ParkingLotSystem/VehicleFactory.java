package ParkingLotSystem;

public class VehicleFactory {
    
    public static Vehicle createVehicle(String vehicleType, String licensePlate, ParkingFeeStrategy feeStrategy) {
        switch (vehicleType.toLowerCase()) {
            case "car":
                return new CarVehicle(licensePlate, feeStrategy);
            case "bike":
                return new BikeVehicle(licensePlate, feeStrategy);
            // Additional vehicle types can be added here
            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType);
        }
    }
}
