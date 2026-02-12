package CarRentalSystem;

import java.sql.Date;
import java.util.Map;

public class ReservationManager {
    private Map<Integer, Reservation> reservations;
    private int reservationId;

    public ReservationManager() {
        this.reservations = new java.util.HashMap<>();
        this.reservationId = 1;
    }

    public Reservation createReservation(User user, Vehicle vehicle, RentalStore pickupStore, RentalStore dropoffStore,
            Date startDate, Date endDate) {
        if (pickupStore.isVehicleAvailable(vehicle.getRegistrationNumber(), startDate, endDate)) {
            Reservation reservation = new Reservation(reservationId++, user, vehicle, pickupStore, dropoffStore,
                    startDate, endDate);
            reservations.put(reservation.getId(), reservation);
            user.addReservation(reservation);
            return reservation;
        }
        throw new IllegalStateException("Vehicle is not available for the selected dates.");
    }

    public void startRental(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.startRental();
        }
    }

    public void completeRental(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.completeRental();
        }
    }

    public void cancelReservation(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.cancelReservation();
        }
    }

    public Reservation getReservation(int reservationId) {
        return reservations.get(reservationId);
    }

    public void confirmReservation(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.confirmReservation();
        }
    }
}
