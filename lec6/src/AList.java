/**
 * Created by Arjun on 1/30/2017.
 */
public class AList {
    private int size;
    private int[] items;

    /** Creates an empty AList. */
    public AList() {
        this.size = 0;
        this.items = new int[100];
    }

    /** Resizes our underlying array to capacity. */
    private void resize(int capacity) {
        int[] a = new int[capacity];
        System.arraycopy(this.items, 0, a, 0, this.size + 1);
        this.items = a;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (items.length == size) {
            resize(this.size + 1);
        }
        this.items[this.size] = x;
        this.size++;
    }

    /** Return the last element in AList. */
    public int getLast() {
        return this.items[this.size - 1];
    }

    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return items[i];
    }

    /** Deletes item from the back of the list and returns it. */
    public int removeLast() {
        int last = this.getLast();
        this.size--;
        return last;
    }

    /** Return the size of the list. */
    public int size() {
        return this.size;
    }

}
