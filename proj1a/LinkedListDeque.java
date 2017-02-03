/**
 * Created by Arjun on 1/29/2017.
 */
public class LinkedListDeque<T> {

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node<T>();
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

    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        else {
            T data = (T)this.sentinel.next.data;
            this.sentinel.next = this.sentinel.next.next;
            this.sentinel.next.prev = this.sentinel;
            this.size--;
            return data;
        }
    }

    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        else {
            T data = (T)this.sentinel.prev.data;
            this.sentinel.prev = this.sentinel.prev.prev;
            this.sentinel.prev.next = this.sentinel;
            this.size--;
            return data;
        }
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

    public T getRecursive(int index) {
        if (index > this.size - 1) {
            return null;
        }
        else {
            return (T)getRecursiveHelper(index, this.sentinel.next);
        }
    }

    private T getRecursiveHelper(int index, Node<T> currentNode) {
        if (index == 0) {
            return currentNode.data;
        }
        else {
            return (T)getRecursiveHelper(index - 1, currentNode.next);
        }
    }

    public void printDeque() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.get(i) + " ");
        }
    }
}
