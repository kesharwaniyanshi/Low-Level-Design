package CarRentalSystem;

import java.sql.Date;

public class Reservation {
    private int id;
    private User user;
    private Vehicle vehicle;
    private RentalStore pickupStore;
    private RentalStore dropoffStore;
    private ReservationStatus status;
    private double totalCost;
    private Date startDate;
    private Date endDate;

    public Reservation(int id, User user, Vehicle vehicle, RentalStore pickupStore, RentalStore dropoffStore,
            Date startDate, Date endDate) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.pickupStore = pickupStore;
        this.dropoffStore = dropoffStore;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ReservationStatus.PENDING;
        // Calculate total cost immediately on reservation creation
        int days = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
        this.totalCost = vehicle.calculateRentalFee(days);
    }

    public void startRental() {
        if (status == ReservationStatus.CONFIRMED) {
            this.status = ReservationStatus.IN_PROGRESS;
            vehicle.setStatus(VehicleStatus.RENTED);
        }
    }

    public void completeRental() {
        if (status == ReservationStatus.IN_PROGRESS) {
            this.status = ReservationStatus.COMPLETED;
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    public void confirmReservation() {
        if (status == ReservationStatus.PENDING) {
            this.status = ReservationStatus.CONFIRMED;
            vehicle.setStatus(VehicleStatus.RESERVED);
        }
    }

    public void cancelReservation() {
        if (status == ReservationStatus.CONFIRMED || status == ReservationStatus.PENDING) {
            this.status = ReservationStatus.CANCELLED;
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getId() {
        return id;
    }

}
