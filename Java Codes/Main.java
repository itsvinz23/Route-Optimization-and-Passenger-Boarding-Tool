import graph.Graph;
import graph.Graph.DijkstraResult;
import model.Airport;
import boarding.BoardingManager;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Build Graph with airports ---
        Graph g = new Graph();

        Airport CMB = new Airport("CMB", "Colombo");
        Airport BLR = new Airport("BLR", "Bengaluru");
        Airport DEL = new Airport("DEL", "Delhi");
        Airport DXB = new Airport("DXB", "Dubai");
        Airport LHR = new Airport("LHR", "London Heathrow");
        Airport JFK = new Airport("JFK", "New York");
        Airport SIN = new Airport("SIN", "Singapore");
        Airport SYD = new Airport("SYD", "Sydney");
        Airport HND = new Airport("HND", "Tokyo Haneda");
        Airport CDG = new Airport("CDG", "Paris Charles de Gaulle");

        List<Airport> airports = Arrays.asList(CMB, BLR, DEL, DXB, LHR, JFK, SIN, SYD, HND, CDG);

        // Add routes (distance km, cost USD approx)
        g.addRoute(CMB, BLR, 700, 120);
        g.addRoute(CMB, DEL, 2400, 280);
        g.addRoute(CMB, DXB, 3300, 400);
        g.addRoute(BLR, DEL, 1700, 200);
        g.addRoute(BLR, SIN, 3200, 350);
        g.addRoute(DEL, LHR, 6700, 700);
        g.addRoute(DXB, LHR, 5500, 600);
        g.addRoute(DXB, JFK, 11000, 900);
        g.addRoute(SIN, SYD, 6200, 750);
        g.addRoute(SIN, HND, 5300, 600);
        g.addRoute(LHR, JFK, 5600, 650);
        g.addRoute(LHR, CDG, 350, 100);
        g.addRoute(HND, SYD, 7800, 850);

        // --- Show airport list ---
        System.out.println("=== Airline Route Optimization Tool ===\n");
        System.out.println("Available Airports:");
        airports.forEach(a -> System.out.println(" - " + a.getCode() + " : " + a.getName()));

        System.out.print("\nEnter Source Airport Code: ");
        String srcCode = sc.nextLine().trim().toUpperCase();
        System.out.print("Enter Destination Airport Code: ");
        String dstCode = sc.nextLine().trim().toUpperCase();

        Airport src = airports.stream().filter(a -> a.getCode().equals(srcCode)).findFirst().orElse(null);
        Airport dst = airports.stream().filter(a -> a.getCode().equals(dstCode)).findFirst().orElse(null);

        if (src == null || dst == null) {
            System.out.println("❌ Invalid airport code entered!");
            return;
        }

        // --- Run Dijkstra ---
        DijkstraResult distRes = g.dijkstra(src, dst, e -> e.getDistance());
        DijkstraResult costRes = g.dijkstra(src, dst, e -> e.getCost());

        System.out.println("\n🛫 Route Results:");
        System.out.println(" - Shortest Path (by distance): " + distRes.path + " | Total km = " + distRes.total);
        System.out.println(" - Cheapest Path (by cost): " + costRes.path + " | Total cost = $" + costRes.total);

        // --- Boarding Manager ---
        BoardingManager bm = new BoardingManager();
        System.out.println("\n=== Passenger Boarding Registration ===");
        System.out.print("How many passengers to register? ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("\nPassenger #" + (i + 1));
            System.out.print(" Name: ");
            String name = sc.nextLine();
            System.out.print(" ID/Passport: ");
            String id = sc.nextLine();
            System.out.print(" Class (BUSINESS/ECONOMY): ");
            String cls = sc.nextLine();

            // register without assistance
            bm.registerPassenger(name, id, cls, LocalDateTime.now(), false);
        }

        // --- Display Boarding Order---
        System.out.println("\n=== Final Boarding Order ===");
        System.out.printf("%-5s %-15s %-15s %-12s%n",
                "No.", "Name", "ID", "Class");
        System.out.println("------------------------------------------------");

        int i = 1;
        while (bm.hasPassengers()) {
            var p = bm.nextToBoard();
            System.out.printf("%-5d %-15s %-15s %-12s%n",
                    i++, p.getName(), p.getId(), p.getTravelClass());
        }
    }
}
