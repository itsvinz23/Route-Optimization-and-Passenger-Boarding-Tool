package boarding;

import model.Passenger;
import java.time.LocalDateTime;
import java.util.*;

public class BoardingManager {
    private final PriorityQueue<Passenger> pq;
    private long seqCounter = 0;


    private double duration; 
    private int capacity;    

    public BoardingManager(double duration, int capacity) {
        this.duration = duration;
        this.capacity = capacity;

        pq = new PriorityQueue<>(
                Comparator.comparingInt(this::getRank).reversed() // max heap
                        .thenComparingLong(Passenger::getArrivalSeq) // FIFO if same rank
        );
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
        return pq.poll(); // highest priority dequeued first
    }

    public boolean hasPassengers() {
        return !pq.isEmpty();
    }

   
    public List<Passenger> getPassengers() {
        List<Passenger> list = new ArrayList<>(pq);
        list.sort(
                Comparator.comparingInt(this::getRank).reversed()
                        .thenComparingLong(Passenger::getArrivalSeq)
        );
        return list;
    }

    /** Optional: display queue in console */
    public void displayBoardingQueue() {
        System.out.printf("\n=== Boarding Queue (Duration: %.1f hrs | Capacity: %d seats) ===%n",
                duration, capacity);

        List<Passenger> list = getPassengers();
        if (list.isEmpty()) {
            System.out.println("No passengers waiting.");
            return;
        }

        System.out.printf("%-5s %-15s %-15s %-12s %-10s%n", "No.", "Name", "ID", "Class", "Assist");
        System.out.println("---------------------------------------------------------");
        int i = 1;
        for (Passenger p : list) {
            System.out.printf("%-5d %-15s %-15s %-12s %-10s%n",
                    i++, p.getName(), p.getId(), p.getTravelClass(),
                    p.isAssistance() ? "🚩" : "");
        }
    }

    //assign rank for max-heap priority
    private int getRank(Passenger p) {
        if (p.getTravelClass().equalsIgnoreCase("BUSINESS") && p.isAssistance()) return 4;
        if (p.getTravelClass().equalsIgnoreCase("BUSINESS")) return 3;
        if (p.getTravelClass().equalsIgnoreCase("ECONOMY") && p.isAssistance()) return 2;
        return 1; // ECONOMY without assistance
    }
}
