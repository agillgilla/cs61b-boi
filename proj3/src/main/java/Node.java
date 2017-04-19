import java.util.ArrayDeque;
import java.util.HashSet;

/**
 * Created by Arjun on 4/14/2017.
 */
public class Node {
    private HashSet<Edge> edges;

    private long id;
    private double lon;
    private double lat;
    private String name;

    public Node(long id, double lon, double lat) {
        this.edges = new HashSet<>();
        this.id = id;
        this.lon = lon;
        this.lat = lat;
        this.name = "NO NAME ASSIGNED";
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEdge(Edge e) {
        this.edges.add(e);
    }

    public Iterable<Long> adjacent() {
        ArrayDeque<Long> adj = new ArrayDeque<>();
        for (Edge e : this.edges) {
            adj.addLast(e.other(this).getId());
        }
        return adj;
    }
}
