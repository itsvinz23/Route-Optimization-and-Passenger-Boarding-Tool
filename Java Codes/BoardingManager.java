package boarding;

import model.Passenger;
import java.time.LocalDateTime;
import java.util.*;

public class BoardingManager {
    private final PriorityQueue<Passenger> pq;
    private long seqCounter = 0;

    public BoardingManager() {
        pq = new PriorityQueue<>((a, b) -> {
            int cmp = Integer.compare(b.getPriorityValue(), a.getPriorityValue());
            if (cmp != 0) return cmp;
            return Long.compare(a.getArrivalSeq(), b.getArrivalSeq()); // FIFO
        });
    }

    public Passenger registerPassenger(String name, String id, String travelClass,
                                       LocalDateTime checkIn, boolean assistance) {
        Passenger p = new Passenger(name, id, travelClass, checkIn, assistance, ++seqCounter);
        pq.add(p);
        return p;
    }

    public Passenger nextToBoard() {
        return pq.poll();
    }

    public List<Passenger> currentBoardingOrder() {
        List<Passenger> list = new ArrayList<>(pq);
        list.sort(pq.comparator());
        return list;
    }

    public boolean hasPassengers() {
        return !pq.isEmpty();
    }
}
