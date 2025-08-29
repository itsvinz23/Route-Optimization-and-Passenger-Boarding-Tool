package model;

import java.time.LocalDateTime;

public class Passenger {
    private final String name;
    private final String id;
    private final String travelClass; // BUSINESS / ECONOMY
    private final LocalDateTime checkIn;
    private final boolean assistance;
    private final long arrivalSeq;

    public Passenger(String name, String id, String travelClass, LocalDateTime checkIn, boolean assistance, long arrivalSeq) {
        this.name = name;
        this.id = id;
        this.travelClass = travelClass;
        this.checkIn = checkIn;
        this.assistance = assistance;
        this.arrivalSeq = arrivalSeq;
    }

    public int getPriorityValue() {
        int p = "BUSINESS".equalsIgnoreCase(travelClass) ? 2 : 1;
        if (assistance) p += 1; // assistance gets extra priority
        return p;
    }

    public String getName() { return name; }
    public String getId() { return id; }
    public String getTravelClass() { return travelClass; }
    public LocalDateTime getCheckIn() { return checkIn; }
    public boolean isAssistance() { return assistance; }
    public long getArrivalSeq() { return arrivalSeq; }

    @Override
    public String toString() {
        return String.format("%s [%s] Class=%s Assist=%s Priority=%d Seq=%d",
                name, id, travelClass, assistance, getPriorityValue(), arrivalSeq);
    }
}
