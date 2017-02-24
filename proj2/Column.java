/**
 * Created by Arjun on 2/22/2017.
 */
public class Column<Item> extends ArrayDeque<Item> {
    private ArrayDeque<Item> elements;
    private String name;

    public Column(String name, Item... args) {
        this.name = name;
        this.elements = new ArrayDeque<>();
        for (Item element : args) {
            this.elements.addLast(element);
        }
    }

    public String getName() {
        return this.name;
    }


}
