import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Graph for storing all of the intersection (vertex) and road (edge) information.
 * Uses your GraphBuildingHandler to convert the XML files into a graph. Your
 * code must include the vertices, adjacent, distance, closest, lat, and lon
 * methods. You'll also need to include instance variables and methods for
 * modifying the graph (e.g. addNode and addEdge).
 *
 * @author Alan Yao, Josh Hug
 */
public class GraphDB {
    /** Your instance variables for storing the graph. You should consider
     * creating helper classes, e.g. Node, Edge, etc. */

    private HashMap<Long, Node> nodes;
    /**
     * Example constructor shows how to create and start an XML parser.
     * You do not need to modify this constructor, but you're welcome to do so.
     * @param dbPath Path to the XML file to be parsed.
     */
    public GraphDB(String dbPath) {
        try {
            File inputFile = new File(dbPath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GraphBuildingHandler gbh = new GraphBuildingHandler(this);
            saxParser.parse(inputFile, gbh);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        clean();
    }

    /**
     * Adds a new Node to the GraphDB.
     * @param id ID of new Node
     * @param lon Longitude of new Node
     * @param lat Latitude of new Node
     * @return new Node instance
     */
    public Node addNode(long id, double lon, double lat) {
        return null;
    }

    /**
     * Adds new Edge between two Nodes to the GraphDB.
     * @param id1
     * @param id2
     */
    public void addEdge(long id1, long id2) {
        return;
    }

    /**
     * Removes a node.
     * @param id The ID of the node to be removed.
     */
    public void removeNode(long id) {
        return;
    }

    /**
     * Adds new Edge between list of Nodes to the GraphDB.
     * @param nodes
     */
    public void addWay(ArrayDeque<Node> nodes) {
        Node[] nodeArray = (Node[]) nodes.toArray();
        Node prevNode = nodeArray[0];
        for (int i = 1; i < nodeArray.length; i++) {
            this.addEdge(prevNode.getId(), nodeArray[i].getId());
        }
    }


    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     *  Remove nodes with no connections from the graph.
     *  While this does not guarantee that any two nodes in the remaining graph are connected,
     *  we can reasonably assume this since typically roads are connected.
     */
    private void clean() {
        // TODO: Your code here.
    }

    /** Returns an iterable of all vertex IDs in the graph. */
    Iterable<Long> vertices() {
        //YOUR CODE HERE
        return this.nodes.keySet();
    }

    /** Returns ids of all vertices adjacent to v. */
    Iterable<Long> adjacent(long v) {
        return null;
    }

    /** Returns the Euclidean distance between vertices v and w, where Euclidean distance
     *  is defined as sqrt( (lonV - lonV)^2 + (latV - latV)^2 ). */
    double distance(long v, long w) {
        return 0;
    }

    /** Returns the vertex id closest to the given longitude and latitude. */
    long closest(double lon, double lat) {
        return 0;
    }

    /** Longitude of vertex v. */
    double lon(long v) {
        return 0;
    }

    /** Latitude of vertex v. */
    double lat(long v) {
        return 0;
    }
}
