package SwiftCargo;

import java.util.*;

public class MainTest {
    public static void main(String[] args) {
        // Membangun peta jaringan jalan antar kota untuk BFS
        RouteFinderbfs.addRoute("Arad", "Zerind", 75);
        RouteFinderbfs.addRoute("Arad", "Sibiu", 140);
        RouteFinderbfs.addRoute("Zerind", "Oradea", 71);
        RouteFinderbfs.addRoute("Oradea", "Sibiu", 151);
        RouteFinderbfs.addRoute("Sibiu", "Fagaras", 99);
        RouteFinderbfs.addRoute("Sibiu", "Rimnicu Vilcea", 80);
        RouteFinderbfs.addRoute("Fagaras", "Bucharest", 211);
        RouteFinderbfs.addRoute("Rimnicu Vilcea", "Pitesti", 97);
        RouteFinderbfs.addRoute("Rimnicu Vilcea", "Craiova", 146);
        RouteFinderbfs.addRoute("Craiova", "Pitesti", 138);
        RouteFinderbfs.addRoute("Bucharest", "Pitesti", 101);
        RouteFinderbfs.addRoute("Bucharest", "Giurgiu", 90);
        RouteFinderbfs.addRoute("Bucharest", "Urziceni", 85);
        RouteFinderbfs.addRoute("Urziceni", "Vaslui", 142);
        RouteFinderbfs.addRoute("Vaslui", "Iasi", 92);
        RouteFinderbfs.addRoute("Iasi", "Neamt", 87);

        // Mencari rute terpendek dari kota asal ke kota tujuan dengan BFS
        String startCityBFS = "Arad";
        String endCityBFS = "Bucharest";
        List<String> shortestRouteBFS = RouteFinderbfs.findShortestRoute(startCityBFS, endCityBFS);

        // Output hasil pencarian BFS
        printResult("BFS", startCityBFS, endCityBFS, shortestRouteBFS);

        // Membangun peta jaringan jalan antar kota untuk DLS
        RouteFinderdls.addRoute("Arad", "Zerind", 75);
        RouteFinderdls.addRoute("Arad", "Sibiu", 140);
        RouteFinderdls.addRoute("Zerind", "Oradea", 71);
        RouteFinderdls.addRoute("Oradea", "Sibiu", 151);
        RouteFinderdls.addRoute("Sibiu", "Fagaras", 99);
        RouteFinderdls.addRoute("Sibiu", "Rimnicu Vilcea", 80);
        RouteFinderdls.addRoute("Fagaras", "Bucharest", 211);
        RouteFinderdls.addRoute("Rimnicu Vilcea", "Pitesti", 97);
        RouteFinderdls.addRoute("Rimnicu Vilcea", "Craiova", 146);
        RouteFinderdls.addRoute("Craiova", "Pitesti", 138);
        RouteFinderdls.addRoute("Bucharest", "Pitesti", 101);
        RouteFinderdls.addRoute("Bucharest", "Giurgiu", 90);
        RouteFinderdls.addRoute("Bucharest", "Urziceni", 85);
        RouteFinderdls.addRoute("Urziceni", "Vaslui", 142);
        RouteFinderdls.addRoute("Vaslui", "Iasi", 92);
        RouteFinderdls.addRoute("Iasi", "Neamt", 87);

        // Mencari rute terpendek dari kota asal ke kota tujuan dengan DLS
        String startCityDLS = "Arad";
        String endCityDLS = "Bucharest";
        int depthLimit = 5;
        List<String> shortestRouteDLS = RouteFinderdls.findShortestRoute(startCityDLS, endCityDLS, depthLimit);

        // Output hasil pencarian DLS
        printResult("DLS", startCityDLS, endCityDLS, shortestRouteDLS);

        // Membangun peta jaringan jalan antar kota untuk UCS
        RouteFinderucs.addRoute("Arad", "Zerind", 75);
        RouteFinderucs.addRoute("Arad", "Sibiu", 140);
        RouteFinderucs.addRoute("Zerind", "Oradea", 71);
        RouteFinderucs.addRoute("Oradea", "Sibiu", 151);
        RouteFinderucs.addRoute("Sibiu", "Fagaras", 99);
        RouteFinderucs.addRoute("Sibiu", "Rimnicu Vilcea", 80);
        RouteFinderucs.addRoute("Fagaras", "Bucharest", 211);
        RouteFinderucs.addRoute("Rimnicu Vilcea", "Pitesti", 97);
        RouteFinderucs.addRoute("Rimnicu Vilcea", "Craiova", 146);
        RouteFinderucs.addRoute("Craiova", "Pitesti", 138);
        RouteFinderucs.addRoute("Bucharest", "Pitesti", 101);
        RouteFinderucs.addRoute("Bucharest", "Giurgiu", 90);
        RouteFinderucs.addRoute("Bucharest", "Urziceni", 85);
        RouteFinderucs.addRoute("Urziceni", "Vaslui", 142);
        RouteFinderucs.addRoute("Vaslui", "Iasi", 92);
        RouteFinderucs.addRoute("Iasi", "Neamt", 87);

        // Mencari rute terpendek dari kota asal ke kota tujuan dengan UCS
        String startCityUCS = "Arad";
        String endCityUCS = "Bucharest";
        List<String> shortestRouteUCS = RouteFinderucs.findShortestRoute(startCityUCS, endCityUCS);

        // Output hasil pencarian UCS
        printResult("UCS", startCityUCS, endCityUCS, shortestRouteUCS);
    }

    // Fungsi untuk mencetak hasil pencarian
    static void printResult(String algorithm, String startCity, String endCity, List<String> shortestRoute) {
        if (shortestRoute != null) {
            System.out.println("Rute terpendek dari " + startCity + " ke " + endCity + " (dengan " + algorithm + "):");
            for (int i = 0; i < shortestRoute.size(); i++) {
                System.out.print(shortestRoute.get(i));
                if (i < shortestRoute.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println("\nTotal jarak: " + calculateDistance(shortestRoute) + " km");
        } else {
            System.out.println("Tidak ada rute yang ditemukan dari " + startCity + " ke " + endCity + " menggunakan " + algorithm);
        }
    }

    // Fungsi untuk menghitung total jarak dari sebuah rute
    static int calculateDistance(List<String> route) {
        int distance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            String city1 = route.get(i);
            String city2 = route.get(i + 1);
            distance += RouteFinderbfs.roadMap.get(city1).get(city2);
        }
        return distance;
    }
}
