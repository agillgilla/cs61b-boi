/**
 * Created by Arjun on 1/29/2017.
 */
public class Node<T> {

    public Node prev;
    public Node next;
    public T data;

    public Node(T data, Node prev, Node next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public Node() {
        this.data = null;
        this.prev = null;
        this.next = null;
    }
}
