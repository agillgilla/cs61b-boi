/**
 * Created by Arjun on 4/14/2017.
 */
public class Edge {

    private Node node1;
    private Node node2;
    private double distance;
    private String name;

    public Edge(Node node1, Node node2, String name) {
        this.node1 = node1;
        this.node2 = node2;
        this.name = name;
    }

    public Node other(Node ref) {
        if (node1.equals(ref)) {
            return node2;
        }
        return node1;
    }
}
