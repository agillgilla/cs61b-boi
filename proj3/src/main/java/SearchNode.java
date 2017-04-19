/**
 * Created by Arjun on 4/18/2017.
 */
public class SearchNode implements Comparable<SearchNode> {
    public static Node target;

    private static final int ADJUST_COEFF = (int) Math.pow(10, 16);

    private Node node;
    private double distFromStart;
    private SearchNode prevNode;
    private double heuristic;

    public SearchNode(Node node, SearchNode prevNode) {
        this.node = node;
        this.prevNode = prevNode;
        if (this.prevNode == null) {
            this.distFromStart = 0;
        } else {
            this.distFromStart = this.prevNode.getDistanceFromStart()
                    + SearchNode.distance(prevNode.getNode(), this.getNode());
        }
        this.heuristic = SearchNode.distance(this.getNode(), target);
    }

    public int compareTo(SearchNode o) {
        return (int)(((this.totalCostExact()) - (o.totalCostExact())) * ADJUST_COEFF);
    }

    public double getDistanceFromStart() {
        return this.distFromStart;
    }

    public SearchNode getPrevNode() {
        return this.prevNode;
    }

    public double heuristic() {
        return this.heuristic;
    }

    public Node getNode() {
        return this.node;
    }

    public int totalCost() {
        //return (int) ((this.getDistanceFromStart() + this.heuristic()) * adjustCoeff);
        return (int) (this.getDistanceFromStart() + this.heuristic());
    }

    public double totalCostExact() {
        return (this.distFromStart + this.heuristic);
    }

    public static double distance(Node nd1, Node nd2) {
        return Math.sqrt(Math.pow(nd1.getLon() - nd2.getLon(), 2)
                + Math.pow(nd1.getLat() - nd2.getLat(), 2));
    }
}
