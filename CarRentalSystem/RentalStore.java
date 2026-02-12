package CarRentalSystem;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalStore {
    private String name;
    private Location location;
    private int id;
    private Map<String, Vehicle> vehicles; // registrationNumber -> Vehicle
    
    public RentalStore(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.vehicles = new HashMap<>();
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles.values()) {
            if (vehicle.getStatus() == VehicleStatus.AVAILABLE) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getRegistrationNumber(), vehicle);
    }

    public void removeVehicle(String registrationNumber) {
        vehicles.remove(registrationNumber);
    }

    public boolean isVehicleAvailable(String registrationNumber , Date startDate, Date endDate) {
        Vehicle vehicle = vehicles.get(registrationNumber);
        return vehicle != null && vehicle.getStatus() == VehicleStatus.AVAILABLE;
    }

    public Vehicle getVehicle(String registrationNumber) {
        return vehicles.get(registrationNumber);
    }

    public int getId() {
        return id;
    }

}
