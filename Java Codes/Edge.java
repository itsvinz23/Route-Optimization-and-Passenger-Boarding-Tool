package model;

public class Edge {
    private final Airport to;
    private final double distance;
    private final double cost;

    public Edge(Airport to, double distance, double cost) {
        this.to = to;
        this.distance = distance;
        this.cost = cost;
    }

    public Airport getTo() { return to; }
    public double getDistance() { return distance; }
    public double getCost() { return cost; }
}
