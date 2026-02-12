package CarRentalSystem;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String name;
    private String email;
    private List<Reservation> reservations;

    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void cancelReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public int getId() {
        return userId;
    }
}
