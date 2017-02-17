/**
 * Created by Arjun on 2/7/2017.
 */
public interface Deque<Item> {

    void addFirst(Item item);

    void addLast(Item item);

    boolean isEmpty();

    int size();

    void printDeque();

    Item removeFirst();

    Item removeLast();

    Item get(int index);

}
