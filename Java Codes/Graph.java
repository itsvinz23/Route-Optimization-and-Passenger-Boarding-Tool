package graph;

import model.Airport;
import model.Edge;

import java.util.*;
import java.util.function.ToDoubleFunction;

public class Graph {
    private final Map<Airport, List<Edge>> adj = new HashMap<>();

    public void addAirport(Airport a) {
        adj.putIfAbsent(a, new ArrayList<>());
    }

    public void addRoute(Airport from, Airport to, double distance, double cost, double duration, int capacity) {
        addAirport(from);
        addAirport(to);
        adj.get(from).add(new Edge(to, distance, cost, duration, capacity));
    }

    public List<Edge> neighbors(Airport a) {
        return adj.getOrDefault(a, Collections.emptyList());
    }

    // Dijkstra: works with distance OR cost (depending on selector)
    public DijkstraResult dijkstra(Airport source, Airport target, ToDoubleFunction<Edge> weightSelector) {
        Map<Airport, Double> dist = new HashMap<>();
        Map<Airport, Airport> prev = new HashMap<>();

        for (Airport a : adj.keySet()) {
            dist.put(a, Double.POSITIVE_INFINITY);
        }
        dist.put(source, 0.0);

        PriorityQueue<Airport> pq = new PriorityQueue<>(Comparator.comparingDouble(dist::get));
        pq.add(source);

        while (!pq.isEmpty()) {
            Airport u = pq.poll();
            if (u.equals(target)) break;

            for (Edge e : neighbors(u)) {
                Airport v = e.getTo();
                double alt = dist.get(u) + weightSelector.applyAsDouble(e);
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    prev.put(v, u);
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }

        List<Airport> path = new ArrayList<>();
        Airport cur = target;
        if (!prev.containsKey(cur) && !cur.equals(source)) {
            return new DijkstraResult(Collections.emptyList(), Double.POSITIVE_INFINITY);
        }
        while (cur != null) {
            path.add(cur);
            cur = prev.get(cur);
        }
        Collections.reverse(path);
        return new DijkstraResult(path, dist.get(target));
    }

    public static class DijkstraResult {
        public final List<Airport> path;
        public final double total;

        public DijkstraResult(List<Airport> path, double total) {
            this.path = path;
            this.total = total;
        }
    }
}

