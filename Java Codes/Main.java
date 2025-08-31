import graph.Graph;
import graph.Graph.DijkstraResult;
import model.Airport;
import boarding.BoardingManager;
import model.Passenger;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
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

        
        g.addRoute(CMB, BLR, 700, 120, 1.5, 180);
        g.addRoute(CMB, DEL, 2400, 280, 3.0, 200);
        g.addRoute(CMB, DXB, 3300, 400, 4.5, 220);
        g.addRoute(BLR, DEL, 1700, 200, 2.5, 150);
        g.addRoute(BLR, SIN, 3200, 350, 4.0, 200);
        g.addRoute(DEL, LHR, 6700, 700, 9.0, 300);
        g.addRoute(DXB, LHR, 5500, 600, 8.0, 280);
        g.addRoute(DXB, JFK, 11000, 900, 14.0, 320);
        g.addRoute(SIN, SYD, 6200, 750, 8.5, 260);
        g.addRoute(SIN, HND, 5300, 600, 7.5, 240);
        g.addRoute(LHR, JFK, 5600, 650, 8.5, 300);
        g.addRoute(LHR, CDG, 350, 100, 1.0, 180);
        g.addRoute(HND, SYD, 7800, 850, 10.0, 280);

        
        System.out.println("=== Airline Route Optimization Tool ===\n");
        displayRouteGraph(airports, g);

       
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

       
        DijkstraResult distRes = g.dijkstra(src, dst, e -> e.getDistance());
        DijkstraResult costRes = g.dijkstra(src, dst, e -> e.getCost());

        // Check if a valid route exists
        if (distRes.path.isEmpty() || distRes.total == Double.POSITIVE_INFINITY) {
            System.out.println("\n❌ No available flight from " + src.getCode() + " to " + dst.getCode());
            System.out.println("⚠️ Please check the route map and select a valid source/destination.");
            return; 
        }

        System.out.println("\n🛫 Route Results:");
        System.out.println(" - Shortest Path (by distance): " + distRes.path + " | Total km = " + distRes.total);
        System.out.println(" - Cheapest Path (by cost): " + costRes.path + " | Total cost = $" + costRes.total);

        // --- Flight details for BoardingManager ---
        System.out.println("\n=== Flight Details ===");
        System.out.print("Enter Flight Duration (hours): ");
        double duration = sc.nextDouble();
        System.out.print("Enter Flight Capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine();

        BoardingManager bm = new BoardingManager(duration, capacity);

        System.out.println("\n=== Passenger Boarding Registration ===");
        System.out.println("Type 'END' at any time to finish registration early.");

        while (true) {
            System.out.print("\nPassenger Name: ");
            String name = sc.nextLine().trim();
            if (name.equalsIgnoreCase("END")) break;

            System.out.print("ID/Passport: ");
            String id = sc.nextLine().trim();
            if (id.equalsIgnoreCase("END")) break;

            System.out.print("Class (BUSINESS/ECONOMY): ");
            String cls = sc.nextLine().trim().toUpperCase();
            if (cls.equalsIgnoreCase("END")) break;

            System.out.print("Do you need special assistance? (Y/N): ");
            String assistStr = sc.nextLine().trim();
            if (assistStr.equalsIgnoreCase("END")) break;

            boolean assist = assistStr.equalsIgnoreCase("Y");

            bm.registerPassenger(name, id, cls, LocalDateTime.now(), assist);
            System.out.println("✅ Passenger registered successfully! (Total registered: " + bmSize(bm) + ")");
        }

        
        System.out.println("\n=== Boarding Process ===");
        int registeredCount = bm.getPassengers().size(); 
        System.out.printf("Flight: %s -> %s | Duration: %.1f hrs | Capacity: %d seats | Registered: %d%n",
                src.getCode(), dst.getCode(), duration, capacity, registeredCount);

        System.out.println("Passenger Boarding Order (Priority + Arrival Sequence):\n");
        System.out.printf("%-5s %-15s %-15s %-12s %-10s %-8s %-20s%n",
                "No.", "Name", "ID", "Class", "Assist", "Priority", "Check-In Time");
        System.out.println("----------------------------------------------------------------------------------");

        int i = 1;
        for (Passenger p : bm.getPassengers()) {
            String assistFlag = p.isAssistance() ? "🚩" : "";
            System.out.printf("%-5d %-15s %-15s %-12s %-10s %-8d %-20s%n",
                    i++, p.getName(), p.getId(), p.getTravelClass(), assistFlag, p.getPriorityValue(),
                    p.getCheckIn().toString().replace("T", " "));
        }

        System.out.println("\n✅ All passengers processed. Boarding complete!");
    }

    private static void displayRouteGraph(List<Airport> airports, Graph g) {
        System.out.println("\n=== Airline Route Map (Unidirectional) ===");
        for (Airport from : airports) {
            List<String> routes = new ArrayList<>();
            for (var e : g.neighbors(from)) {
                routes.add(e.getTo().getCode() + " (Distance: " + e.getDistance() + " km, Cost: RS." + e.getCost() + ")");
            }
            if (routes.isEmpty()) {
                System.out.println(from.getCode() + " -> No outgoing flights");
            } else {
                System.out.println(from.getCode() + " -> " + String.join(", ", routes));
            }
        }
        System.out.println("⚠️ Note: Routes are unidirectional. Some reverse flights may not exist!");
    }

   
    private static int bmSize(BoardingManager bm) {
        try {
            java.lang.reflect.Field pqField = BoardingManager.class.getDeclaredField("pq");
            pqField.setAccessible(true);
            PriorityQueue<?> pq = (PriorityQueue<?>) pqField.get(bm);
            return pq.size();
        } catch (Exception e) {
            return 0;
        }
    }
}
