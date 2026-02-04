package ParkingLotSystem;

public class BasicFeeStrategy implements ParkingFeeStrategy{
    
    @Override
    public double calculateFee(String vehicleType, int duration, DurationType durationType) {
        double fee = 0.0;
        int hours = 0;

        if (durationType == DurationType.DAYS) {
            hours = duration * 24;
        } else {
            hours = duration;
        }

        switch (vehicleType.toLowerCase()) {
            case "car":
                fee = hours * 2.0; // $2 per hour for cars
                break;
            case "bike":
                fee = hours * 1.0; // $1 per hour for bikes
                break;
            case "truck":
                fee = hours * 3.0; // $3 per hour for trucks
                break;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }

        return fee;
    }
}
