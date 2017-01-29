/**
 * Created by Arjun on 1/29/2017.
 */
public class LinkedListDeque<T> {

    public Node<T> sentinel;
    public int size;

    public LinkedListDeque() {
        this.sentinel = new Node();
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;
    }

    public void addFirst(T data) {
        Node<T> addedNode = new Node<T>(data, this.sentinel, this.sentinel.next);
        this.sentinel.next = addedNode;
        addedNode.next.prev = addedNode;
        this.size++;
    }

    public void addLast(T data) {
        Node<T> addedNode = new Node<T>(data, this.sentinel.prev, this.sentinel);
        this.sentinel.prev.next = addedNode;
        this.sentinel.prev = addedNode;
        this.size++;
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int size() {
        return this.size;
    }

    public T get(int index) {
        if (index > this.size - 1) {
            return null;
        }
        else {
            Node<T> currNode = this.sentinel.next;
            for (int i = 0; i < index; i++) {
                currNode = currNode.next;
            }
            return currNode.data;
        }
    }

    public void printDeque() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.get(i) + " ");
        }
    }
}
