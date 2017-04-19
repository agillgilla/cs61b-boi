import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayDeque;
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
        this.nodes = new HashMap<>();
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
     * Adds a new Node to the GraphDB from parameters.
     * @param id ID of new Node
     * @param lon Longitude of new Node
     * @param lat Latitude of new Node
     * @return new Node instance
     */
    public Node addNode(long id, double lon, double lat) {
        Node nd = new Node(id, lon, lat);
        this.nodes.put(id, nd);
        return nd;
    }

    /**
     * Adds a new Node to the GraphDB
     * @param nd The new Node instance.
     */
    public void addNode(Node nd) {
        this.nodes.put(nd.getId(), nd);
    }

    /**
     * Adds new Edge between two Nodes to the GraphDB.
     * @param id1
     * @param id2
     */
    public void addEdge(long id1, long id2, String name) {
        Edge edge = new Edge(this.nodes.get(id1), this.nodes.get(id2), name);
        this.nodes.get(id1).addEdge(edge);
        this.nodes.get(id2).addEdge(edge);
    }

    /**
     * Removes a node.
     * @param id The ID of the node to be removed.
     */
    public void removeNode(long id) {
        this.nodes.remove(id);
    }

    /**
     * Adds new Edge between list of Nodes to the GraphDB.
     * @param nodeIds
     */
    public void addWay(ArrayDeque<Long> nodeIds, String name) {
        Long[] nodeArray = nodeIds.toArray(new Long[nodeIds.size()]);
        for (int i = 1; i < nodeArray.length; i++) {
            this.addEdge(nodeArray[i - 1], nodeArray[i], name);
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
        ArrayDeque<Long> removeList = new ArrayDeque<>();
        for (Long vertexID : this.vertices()) {
            if (this.nodes.get(vertexID).notConnected()) {
                removeList.add(vertexID);
            }
        }
        for (Long vertexToRemove : removeList) {
            this.removeNode(vertexToRemove);
        }
    }

    /** Returns an iterable of all vertex IDs in the graph. */
    Iterable<Long> vertices() {
        return this.nodes.keySet();
    }

    /** Returns ids of all vertices adjacent to v. */
    Iterable<Long> adjacent(long v) {
        return this.nodes.get(v).adjacent();
    }

    /** Returns the Euclidean distance between vertices v and w, where Euclidean distance
     *  is defined as sqrt( (lonV - lonV)^2 + (latV - latV)^2 ). */
    double distance(long v, long w) {
        Node nd1 = this.nodes.get(v);
        Node nd2 = this.nodes.get(w);
        return Math.sqrt(Math.pow(nd1.getLon() - nd2.getLon(), 2) + Math.pow(nd1.getLat() - nd2.getLat(), 2));
    }

    /** Returns the Euclidean distance between vertex v and latitude lat and longitude lon.
     *
     * @param v The vertex node
     * @param lat The target latitude
     * @param lon The target longitude
     * @return
     */
    double distance(long v, double lon, double lat) {
        Node nd1 = this.nodes.get(v);
        return Math.sqrt(Math.pow(nd1.getLon() - lon, 2) + Math.pow(nd1.getLat() - lat, 2));
    }

    /** Returns the vertex id closest to the given longitude and latitude. */
    long closest(double lon, double lat) {
        long closest = -1;
        for (Long vertex : this.vertices()) {
            if (closest == -1) {
                closest = vertex;
            } else {
                if (distance(closest, lon, lat) > distance(vertex, lon, lat)) {
                    closest = vertex;
                }
            }
        }
        return closest;
    }

    /** Longitude of vertex v. */
    double lon(long v) {
        return this.nodes.get(v).getLon();
    }

    /** Latitude of vertex v. */
    double lat(long v) {
        return this.nodes.get(v).getLat();
    }
}
