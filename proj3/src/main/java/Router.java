import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * This class provides a shortestPath method for finding routes between two points
 * on the map. Start by using Dijkstra's, and if your code isn't fast enough for your
 * satisfaction (or the autograder), upgrade your implementation by switching it to A*.
 * Your code will probably not be fast enough to pass the autograder unless you use A*.
 * The difference between A* and Dijkstra's is only a couple of lines of code, and boils
 * down to the priority you use to order your vertices.
 */
public class Router {

    /**
     * Return a LinkedList of <code>Long</code>s representing the shortest path from st to dest, 
     * where the longs are node IDs.
     */
    public static LinkedList<Long> shortestPath(GraphDB g,
                                                double stlon,
                                                double stlat,
                                                double destlon,
                                                double destlat) {
        Node target = g.getNode(g.closest(destlon, destlat));
        SearchNode.target = target;

        PriorityQueue<SearchNode> queue = new PriorityQueue<>();
        SearchNode start = new SearchNode(g.getNode(g.closest(stlon, stlat)), null);
        queue.add(start);

        HashSet<Node> closedSet = new HashSet<>();

        SearchNode current = null;
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.getNode().equals(target)) {
                return Router.tracePath(current, start);
            }

            closedSet.add(current.getNode());
            for (Long neighbor : current.getNode().adjacent()) {
                if (!g.getNode(neighbor).equals(current.getNode())) {
                    if ((current.getPrevNode() == null
                            || !g.getNode(neighbor).equals(current.getPrevNode().getNode()))
                        && !closedSet.contains(g.getNode(neighbor))) {
                        //System.out.println("CURRENT DistToGoal: " + current.heuristic());
                        //System.out.println("CURRENT TotalCost: " + current.totalCost());
                        queue.add(new SearchNode(g.getNode(neighbor), current));

                    }
                }
            }
        }
        return null;
    }

    public static LinkedList<Long> tracePath(SearchNode end, SearchNode start) {
        ArrayDeque<Long> path = new ArrayDeque<>();
        SearchNode current = end;
        while (!current.equals(start)) {
            path.addFirst(current.getNode().getId());
            current = current.getPrevNode();
        }
        path.addFirst(start.getNode().getId());

        LinkedList<Long> linkedPath = new LinkedList<>();
        for (Long id : path) {
            linkedPath.add(id);
        }
        return linkedPath;
    }
}
