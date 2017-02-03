/**
 * Created by Arjun on 1/29/2017.
 */
public class LinkedListDeque<Item> {

    private Node<Item> sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node<Item>();
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;
    }

    public void addFirst(Item data) {
        Node<Item> addedNode = new Node<Item>(data, this.sentinel, this.sentinel.next);
        this.sentinel.next = addedNode;
        addedNode.next.prev = addedNode;
        this.size++;
    }

    public void addLast(Item data) {
        Node<Item> addedNode = new Node<Item>(data, this.sentinel.prev, this.sentinel);
        this.sentinel.prev.next = addedNode;
        this.sentinel.prev = addedNode;
        this.size++;
    }

    public Item removeFirst() {
        if (this.size == 0) {
            return null;
        } else {
            Item data = (Item) this.sentinel.next.data;
            this.sentinel.next = this.sentinel.next.next;
            this.sentinel.next.prev = this.sentinel;
            this.size--;
            return data;
        }
    }

    public Item removeLast() {
        if (this.size == 0) {
            return null;
        } else {
            Item data = (Item) this.sentinel.prev.data;
            this.sentinel.prev = this.sentinel.prev.prev;
            this.sentinel.prev.next = this.sentinel;
            this.size--;
            return data;
        }
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return this.size;
    }

    public Item get(int index) {
        if (index > this.size - 1) {
            return null;
        } else {
            Node<Item> currNode = this.sentinel.next;
            for (int i = 0; i < index; i++) {
                currNode = currNode.next;
            }
            return currNode.data;
        }
    }

    public Item getRecursive(int index) {
        if (index > this.size - 1) {
            return null;
        } else {
            return (Item) getRecursiveHelper(index, this.sentinel.next);
        }
    }

    private Item getRecursiveHelper(int index, Node<Item> currentNode) {
        if (index == 0) {
            return currentNode.data;
        } else {
            return (Item) getRecursiveHelper(index - 1, currentNode.next);
        }
    }

    public void printDeque() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.get(i) + " ");
        }
    }
}
