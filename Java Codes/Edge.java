package model;

public class Edge {
    private final Airport to;
    private final double distance;
    private final double cost;
    private final double duration; // in hours
    private final int capacity;    // max seats

    public Edge(Airport to, double distance, double cost, double duration, int capacity) {
        this.to = to;
        this.distance = distance;
        this.cost = cost;
        this.duration = duration;
        this.capacity = capacity;
    }

    public Airport getTo() { return to; }
    public double getDistance() { return distance; }
    public double getCost() { return cost; }
    public double getDuration() { return duration; }
    public int getCapacity() { return capacity; }
}
