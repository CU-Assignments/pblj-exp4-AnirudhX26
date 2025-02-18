class TicketBookingSystem {
    private static final int SEATS = 10;
    private int availableSeats = SEATS;

    public synchronized boolean bookTicket(String customerType) {
        if (availableSeats > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            availableSeats--;
            System.out.println(customerType + " booked a seat. Available seats: " + availableSeats);
            return true;
        } else {
            System.out.println(customerType + " tried to book, but no seats are available.");
            return false;
        }
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem ticketBookingSystem;
    private String customerType;

    public BookingThread(TicketBookingSystem ticketBookingSystem, String customerType, int priority) {
        this.ticketBookingSystem = ticketBookingSystem;
        this.customerType = customerType;
        this.setPriority(priority);
    }

    @Override
    public void run() {
        while (ticketBookingSystem.getAvailableSeats() > 0) {
            boolean booked = ticketBookingSystem.bookTicket(customerType);
            if (booked) {
                break;
            }
        }
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem ticketBookingSystem = new TicketBookingSystem();

        BookingThread vipCustomer1 = new BookingThread(ticketBookingSystem, "VIP Customer 1", Thread.MAX_PRIORITY);
        BookingThread vipCustomer2 = new BookingThread(ticketBookingSystem, "VIP Customer 2", Thread.MAX_PRIORITY);
        BookingThread regularCustomer1 = new BookingThread(ticketBookingSystem, "Regular Customer 1", Thread.NORM_PRIORITY);
        BookingThread regularCustomer2 = new BookingThread(ticketBookingSystem, "Regular Customer 2", Thread.NORM_PRIORITY);

        vipCustomer1.start();
        vipCustomer2.start();
        regularCustomer1.start();
        regularCustomer2.start();

        try {
            vipCustomer1.join();
            vipCustomer2.join();
            regularCustomer1.join();
            regularCustomer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Booking process completed.");
    }
}
