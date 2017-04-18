import java.util.HashSet;

/**
 * Created by Arjun on 4/14/2017.
 */
public class Node {
    private HashSet<Edge> edges;

    private long id;
    private double lon;
    private double lat;

    public Node(long id, double lon, double lat) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
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

    public long getId() {
        return this.id;
    }
}
