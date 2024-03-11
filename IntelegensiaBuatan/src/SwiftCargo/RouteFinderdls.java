package SwiftCargo;

import java.util.*;

public class RouteFinderdls {
    // Representasi graf jaringan jalan antar kota
    static Map<String, Map<String, Integer>> roadMap = new HashMap<>();

    // Fungsi untuk menambahkan rute baru antara dua kota
    static void addRoute(String city1, String city2, int distance) {
        roadMap.putIfAbsent(city1, new HashMap<>());
        roadMap.putIfAbsent(city2, new HashMap<>());
        roadMap.get(city1).put(city2, distance);
        roadMap.get(city2).put(city1, distance); // asumsi jalan dua arah
    }

    // Algoritma Depth-Limited Search untuk mencari rute terpendek
    static List<String> findShortestRoute(String start, String end, int depthLimit) {
        Stack<String> stack = new Stack<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (current.equals(end)) {
                return buildRoute(parent, start, end);
            }
            if (parent.getOrDefault(current, "").equals("")) {
                Map<String, Integer> neighbors = roadMap.getOrDefault(current, new HashMap<>());
                for (String neighbor : neighbors.keySet()) {
                    if (!visited.contains(neighbor) && depthLimit > 0) {
                        stack.push(neighbor);
                        visited.add(neighbor);
                        parent.put(neighbor, current);
                    }
                }
            }
            depthLimit--;
        }
        return null; // Jika tidak ditemukan rute
    }

    // Fungsi untuk membangun rute dari informasi parent
    static List<String> buildRoute(Map<String, String> parent, String start, String end) {
        List<String> route = new ArrayList<>();
        String current = end;
        while (!current.equals(start)) {
            route.add(current);
            current = parent.get(current);
        }
        route.add(start);
        Collections.reverse(route);
        return route;
    }

    public static void main(String[] args) {
        // Membangun peta jaringan jalan antar kota 
        addRoute("Arad", "Zerind", 75);
        addRoute("Arad", "Sibiu", 140);
        addRoute("Zerind", "Oradea", 71);
        addRoute("Oradea", "Sibiu", 151);
        addRoute("Sibiu", "Fagaras", 99);
        addRoute("Sibiu", "Rimnicu Vilcea", 80);
        addRoute("Fagaras", "Bucharest", 211);
        addRoute("Rimnicu Vilcea", "Pitesti", 97);
        addRoute("Rimnicu Vilcea", "Craiova", 146);
        addRoute("Craiova", "Pitesti", 138);
        addRoute("Bucharest", "Pitesti", 101);
        addRoute("Bucharest", "Giurgiu", 90);
        addRoute("Bucharest", "Urziceni", 85);
        addRoute("Urziceni", "Vaslui", 142);
        addRoute("Vaslui", "Iasi", 92);
        addRoute("Iasi", "Neamt", 87);

        // Mencari rute terpendek dari kota asal ke kota tujuan dengan batasan kedalaman
        String startCity = "Bucharest";
        String endCity = "Oradia";
        int depthLimit = 1000;
        List<String> shortestRoute = findShortestRoute(startCity, endCity, depthLimit);

        // Output hasil pencarian
        if (shortestRoute != null) {
            System.out.println("Rute terpendek dari " + startCity + " ke " + endCity + " (dengan batasan kedalaman " + depthLimit + "):");
            for (String city : shortestRoute) {
                System.out.print(city + " -> ");
            }
            System.out.println("\nTotal jarak: " + calculateDistance(shortestRoute) + " km");
        } else {
            System.out.println("Tidak ada rute yang ditemukan dari " + startCity + " ke " + endCity + " dengan batasan kedalaman " + depthLimit);
        }
    }

    // Fungsi untuk menghitung total jarak dari sebuah rute
    static int calculateDistance(List<String> route) {
        int distance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            String city1 = route.get(i);
            String city2 = route.get(i + 1);
            distance += roadMap.get(city1).get(city2);
        }
        return distance;
    }
}
