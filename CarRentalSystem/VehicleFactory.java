package CarRentalSystem;

public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type, String registrationNumber, String model, double baseRentalPrice) {
        switch (type) {
            case ECONOMY:
                return new EconomyVehicle(registrationNumber, model, baseRentalPrice);
            case SUV:
                return new SUVVehicle(registrationNumber, model, baseRentalPrice);
            default:
                throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}
