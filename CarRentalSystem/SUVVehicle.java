package CarRentalSystem;

public class SUVVehicle extends Vehicle {
    private static final double RATE_MULTIPLIER = 3.0;

    public SUVVehicle(String registrationNumber, String model, double baseRentalPrice) {
        super(registrationNumber, model, VehicleType.SUV, baseRentalPrice);
    }

    @Override
    public double calculateRentalFee(int days) {
        return getBaseRentalPrice() * days * RATE_MULTIPLIER;
    }
}
