package main.java.systemdesign.assignment_problems;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Week 8 - Problem 3: Campus Premiere Ticket Counter
 * Manages seat bookings, pricing tiers, double-booking prevention, and cancellations.
 */
public class CampusTicketCounter {

    public enum SeatType {
        REGULAR(150),
        PREMIUM(250),
        RECLINER(400);

        private final int price;
        SeatType(int price) { this.price = price; }
        public int getPrice() { return price; }
    }

    public static class Seat {
        private final String seatNumber;
        private final SeatType type;
        private boolean booked;

        public Seat(String seatNumber, SeatType type) {
            this.seatNumber = seatNumber;
            this.type = type;
            this.booked = false;
        }

        public String getSeatNumber() { return seatNumber; }
        public SeatType getType() { return type; }
        public boolean isBooked() { return booked; }
        public void setBooked(boolean booked) { this.booked = booked; }
    }

    public static class Customer {
        private final String name;
        public Customer(String name) { this.name = name; }
        public String getName() { return name; }
    }

    public static class Booking {
        private final Customer customer;
        private final Show show;
        private final List<Seat> seats;
        private final int totalAmount;
        private boolean active;

        public Booking(Customer customer, Show show, List<Seat> seats, int totalAmount) {
            this.customer = customer;
            this.show = show;
            this.seats = new ArrayList<>(seats);
            this.totalAmount = totalAmount;
            this.active = true;
        }

        public Customer getCustomer() { return customer; }
        public Show getShow() { return show; }
        public List<Seat> getSeats() { return seats; }
        public int getTotalAmount() { return totalAmount; }
        public boolean isActive() { return active; }

        public boolean cancel(LocalDateTime cancelTime) {
            if (!active) {
                System.out.println("Cancellation failed: booking already cancelled.");
                return false;
            }
            if (cancelTime.isAfter(show.getShowTime())) {
                System.out.println("Cancellation failed: cannot cancel after show has started.");
                return false;
            }
            this.active = false;
            for (Seat seat : seats) {
                seat.setBooked(false);
            }
            System.out.println("Booking cancelled for " + customer.getName() + ". Seats released.");
            return true;
        }
    }

    public static class Show {
        private final String showName;
        private final LocalDateTime showTime;
        private final Map<String, Seat> seats = new HashMap<>();

        public Show(String showName, LocalDateTime showTime) {
            this.showName = showName;
            this.showTime = showTime;
        }

        public void addSeat(String seatNumber, SeatType type) {
            seats.put(seatNumber, new Seat(seatNumber, type));
        }

        public LocalDateTime getShowTime() { return showTime; }

        public Booking bookSeats(Customer customer, List<String> seatNumbers) {
            if (seatNumbers == null || seatNumbers.isEmpty()) {
                System.out.println("Booking failed: no seats selected.");
                return null;
            }
            if (seatNumbers.size() > 6) {
                System.out.println("Booking failed: maximum 6 seats allowed per booking.");
                return null;
            }

            // Check availability
            List<Seat> toBook = new ArrayList<>();
            int total = 0;
            for (String sNum : seatNumbers) {
                Seat seat = seats.get(sNum);
                if (seat == null) {
                    System.out.println("Booking failed: seat " + sNum + " does not exist.");
                    return null;
                }
                if (seat.isBooked()) {
                    System.out.println("Booking failed: seat " + sNum + " is already booked.");
                    return null;
                }
                toBook.add(seat);
                total += seat.getType().getPrice();
            }

            // Mark booked
            for (Seat seat : toBook) {
                seat.setBooked(true);
            }

            System.out.println(customer.getName() + " booked " + seatNumbers + " for Rs " + total + ".");
            return new Booking(customer, this, toBook, total);
        }
    }

    public static void main(String[] args) {
        LocalDateTime showTime = LocalDateTime.now().plusHours(2);
        Show show = new Show("Interstellar Premiere", showTime);
        show.addSeat("A1", SeatType.REGULAR);
        show.addSeat("A2", SeatType.REGULAR);
        show.addSeat("F5", SeatType.PREMIUM);
        show.addSeat("R1", SeatType.RECLINER);

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        // 1. Asha books A1, A2 Regular and F5 Premium. Total: 150 + 150 + 250 = 550
        List<String> ashaSeats = new ArrayList<>();
        ashaSeats.add("A1");
        ashaSeats.add("A2");
        ashaSeats.add("F5");
        Booking ashaBooking = show.bookSeats(asha, ashaSeats);

        // 2. Ravi tries A2; reject
        List<String> raviSeatsA2 = new ArrayList<>();
        raviSeatsA2.add("A2");
        show.bookSeats(ravi, raviSeatsA2);

        // 3. Ravi books R1 Recliner for Rs 400
        List<String> raviSeatsR1 = new ArrayList<>();
        raviSeatsR1.add("R1");
        show.bookSeats(ravi, raviSeatsR1);

        // 4. Asha cancels before showtime
        ashaBooking.cancel(LocalDateTime.now());

        // 5. Neha books A2 Regular for Rs 150
        List<String> nehaSeats = new ArrayList<>();
        nehaSeats.add("A2");
        show.bookSeats(neha, nehaSeats);
    }
}
