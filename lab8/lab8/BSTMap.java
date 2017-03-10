package lab8;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Arjun on 3/10/2017.
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public V get(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo((K) x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return (V) x.val;
        }
    }

    public int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.size;
        }
    }

    public Node put(Node x, K key, V val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo((K) x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Node delete(Node x, K key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left  = delete(x.left,  key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left  == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    public Node max(Node x) {
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }
    ///////////////////////BSTMAP METHODS/////////////////////

    public BSTMap() {
    }

    public void clear() {
        this.root = null;
    }

    public void put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public V get(K key) {
        return get(root, key);
    }

    public int size() {
        return size(this.root);
    }

    public void printInOrder() {

    }

    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        root = delete(root, key);
    }

    public Set<K> keySet() {
        throw new UnsupportedOperationException("keySet() not supported");
    }

    public Iterator iterator() {
        throw new UnsupportedOperationException("iterator() not supported");
    }

    public V remove(K key) {
        throw new UnsupportedOperationException("remove(K key) not supported.");
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException("remove(K key, V value) not supported.");
    }
}
