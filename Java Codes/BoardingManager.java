package boarding;

import model.Passenger;
import java.time.LocalDateTime;
import java.util.*;

public class BoardingManager {
    private final PriorityQueue<Passenger> pq;
    private long seqCounter = 0;

    // Add flight info
    private double duration; // hours
    private int capacity;    // seats

    public BoardingManager(double duration, int capacity) {
        this.duration = duration;
        this.capacity = capacity;

        pq = new PriorityQueue<>((a, b) -> {
            int cmp = Integer.compare(b.getPriorityValue(), a.getPriorityValue());
            if (cmp != 0) return cmp;
            return Long.compare(a.getArrivalSeq(), b.getArrivalSeq()); // FIFO
        });
    }

    public Passenger registerPassenger(String name, String id, String travelClass,
                                       LocalDateTime checkIn, boolean assistance) {
        if (pq.size() >= capacity) {
            System.out.println("❌ Flight full! Cannot add more passengers.");
            return null;
        }
        Passenger p = new Passenger(name, id, travelClass, checkIn, assistance, ++seqCounter);
        pq.add(p);
        return p;
    }

    public Passenger nextToBoard() {
        return pq.poll();
    }

    public boolean hasPassengers() {
        return !pq.isEmpty();
    }

    // Display passenger list with flight info
    public void displayBoardingQueue() {
        System.out.printf("\n=== Boarding Queue (Duration: %.1f hrs | Capacity: %d seats) ===%n",
                duration, capacity);

        if (pq.isEmpty()) {
            System.out.println("No passengers waiting.");
            return;
        }

        List<Passenger> list = new ArrayList<>(pq);
        list.sort(pq.comparator());

        System.out.printf("%-5s %-15s %-15s %-12s %-10s%n", "No.", "Name", "ID", "Class", "Assist");
        System.out.println("---------------------------------------------------------");
        int i = 1;
        for (Passenger p : list) {
            System.out.printf("%-5d %-15s %-15s %-12s %-10s%n",
                    i++, p.getName(), p.getId(), p.getTravelClass(),
                    p.isAssistance() ? "🚩" : "");
        }
    }
    public List<Passenger> getPassengers() {
        List<Passenger> list = new ArrayList<>(pq);
        list.sort(pq.comparator()); // maintain priority order
        return list;
    }

}
