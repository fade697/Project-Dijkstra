package application;

import javafx.util.Pair;

import java.util.*;

public class Dijkstra {
    private PriorityQueue<Adjacent> openList;
    private City start;
    private City target;
    private Map<Pair<String, String>, Double> DijkstraMap;

    public Dijkstra(Map<Pair<String, String>, Double> hueristicMap) {
        this.DijkstraMap = hueristicMap;
        openList = new PriorityQueue<Adjacent>();
    }

    public TableEntry[] findPath(City start, City target, TableEntry[] table) {
        this.target = target;
        this.start = start;
        table[start.cityEntry].distance = 0;
        openList.add(new Adjacent(start, 0));
        while (!openList.isEmpty()) {
            Adjacent current = openList.poll();
            int CityEntry = current.getCity().cityEntry;
            if (table[CityEntry].known)
                continue;
            table[CityEntry].known = true;

            if (isFinalDestination(current.getCity())) {
                return table;
            } else {
                addAdjacents(current.getCity(), table);
            }
        }

        return table;
    }

    private void addAdjacents(City current, TableEntry[] table) {
        double edgeDis = -1;
        double newDis = -1;
        for (Adjacent adj : current.adjacent) {
            if (!table[adj.getCity().cityEntry].known) {
                edgeDis = adj.getDistance();
                newDis = table[current.cityEntry].distance + edgeDis;
                if (newDis < table[adj.getCity().cityEntry].distance) {
                    table[adj.getCity().cityEntry].distance = newDis;
                    table[adj.getCity().cityEntry].path = current;
                }
                openList.add(new Adjacent(adj.getCity(), (float) (table[adj.getCity().cityEntry].distance)));
            }
        }
    }



    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double radius = 6371.0; // Radius of the Earth in kilometers

        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double dlon = lon2Rad - lon1Rad;
        double dlat = lat2Rad - lat1Rad;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(dlon / 2) * Math.sin(dlon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = radius * c;
//        System.out.println("Distance calculating"+distance);
        return distance;
    }

    private boolean isFinalDestination(City currentNode) {
        return currentNode.equals(target);
    }

    public String getStart() {
        return start.getName();
    }

    public String getTarget() {
        return target.getName();
    }
}
