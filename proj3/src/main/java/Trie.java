import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by Arjun on 4/19/2017.
 */

public class Trie {

    protected final Map<Character, Trie> children;
    protected String value;
    protected boolean terminal = false;
    protected HashSet<Node> nodes;

    public Trie() {
        this(null);
    }

    private Trie(String value) {
        this.value = value;
        children = new HashMap<Character, Trie>();
        this.nodes = new HashSet<>();
    }

    public void generateNodeTree(GraphDB graph) {
        Collection<Node> nodes = graph.getDirtyNodes();
        for (Node node : nodes) {
            if (!node.getName().equals("none")) {
                this.insert(node);
            }
        }
    }

    protected void add(char c) {
        String val;
        if (this.value == null) {
            val = Character.toString(c);
        } else {
            val = this.value + c;
        }
        children.put(c, new Trie(val));
    }

    public void insert(Node nd) {
        String word = GraphDB.cleanString(nd.getName());
        if (word == null) {
            throw new IllegalArgumentException("Cannot add null to a Trie");
        }
        Trie node = this;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.add(c);
            }
            node = node.children.get(c);
        }
        node.terminal = true;
        node.addNode(nd);
    }

    public Set<Node> find(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                //return "";
                return null;
            }
            node = node.children.get(c);
        }
        //return node.value;
        return node.getNodes();
    }

    public List<Node> autoComplete(String prefix) {
        Trie node = this;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return Collections.emptyList();
            }
            node = node.children.get(c);
        }
        return node.allPrefixes();
    }

    protected List<Node> allPrefixes() {
        //List<String> results = new ArrayList<String>();
        List<Node> nodeResults = new ArrayList<Node>();
        if (this.terminal) {
            //results.add(this.value);
            for (Node node : this.nodes) {
                nodeResults.add(node);
            }
                    }
        for (Entry<Character, Trie> entry : children.entrySet()) {
            Trie child = entry.getValue();
            Collection<Node> childPrefixes = child.allPrefixes();
            //results.addAll(childPrefixes);
            nodeResults.addAll(childPrefixes);
        }
        return nodeResults;
    }

    public void addNode(Node nd) {
        this.nodes.add(nd);
    }

    public HashSet<Node> getNodes() {
        return this.nodes;
    }

    public static void main(String[] args) {
        Trie ac = new Trie();
        /*ac.insert("abraham");
        ac.insert("abscam");
        ac.insert("absolute");
        ac.insert("failure");

        for (String suffix : ac.autoComplete("abs")) {
            System.out.println(suffix);
        }
        */
    }
}

