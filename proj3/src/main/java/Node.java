import java.util.HashSet;

/**
 * Created by Arjun on 4/14/2017.
 */
public class Node {
    private HashSet<Way> ways;

    private long id;
    private double lon;
    private double lat;

    public Node(long id, double lon, double lat) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
    }
}
