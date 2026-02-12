package CarRentalSystem;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalSystem {
    private static RentalSystem instance;
    private List<RentalStore> stores;
    private VehicleFactory vehicleFactory;
    private ReservationManager reservationManager;
    private PaymentProcessor paymentProcessor;
    private Map<Integer, User> users;
    private int nextUserId;

    private RentalSystem() {
        this.stores = new ArrayList<>();
        this.vehicleFactory = new VehicleFactory();
        this.reservationManager = new ReservationManager();
        this.paymentProcessor = new PaymentProcessor();
        this.users = new HashMap<>();
        this.nextUserId = 1;
    }

    public static synchronized RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }

    public List<RentalStore> getRentalStores() {
        return stores;
    }

    public void addRentalStore(RentalStore store) {
        stores.add(store);
    }

    public void removeRentalStore(RentalStore store) {
        stores.remove(store);
    }

    public RentalStore getRentalStoreById(int id) {
        for (RentalStore store : stores) {
            if (store.getId() == id) {
                return store;
            }
        }
        return null;
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

    public Reservation createReservation(int userId, String vehicleRegistration,
            int pickupStoreId, int returnStoreId, Date startDate, Date endDate) {
        User user = getUser(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        RentalStore pickupStore = getRentalStoreById(pickupStoreId);
        RentalStore returnStore = getRentalStoreById(returnStoreId);
        if (pickupStore == null || returnStore == null) {
            throw new IllegalArgumentException("Invalid store ID");
        }
        if (pickupStore.isVehicleAvailable(vehicleRegistration, startDate, endDate)) {
            Vehicle vehicle = pickupStore.getVehicle(vehicleRegistration);
            return reservationManager.createReservation(user, vehicle, pickupStore, returnStore, startDate, endDate);
        } else {
            throw new IllegalArgumentException("Vehicle not available for the selected dates");
        }
    }

    public void registerUser(User user) {
        int userID = user.getId();
        if (users.containsKey(userID)) {
            System.out.println("User with id : " + userID + "Already exists in the system");
            return;
        }
        users.put(userID, user);
    }

    public boolean processPayment(int reservationId, PaymentStrategy paymentMethod) {
        Reservation reservation = reservationManager.getReservation(reservationId);
        if (reservation != null) {
            double amount = reservation.getTotalCost();
            boolean paymentSuccess = paymentProcessor.processPayment(paymentMethod, amount);
            if (paymentSuccess) {
                reservation.confirmReservation();
                System.out.println("Payment successful. Reservation confirmed.");
                return true;
            } else {
                System.out.println("Payment failed. Please try again.");
                return false;
            }
        } else {
            System.out.println("Reservation not found.");
        }
        return false;
    }

    public ReservationManager getReservationManager() {
        return reservationManager;
    }

}
