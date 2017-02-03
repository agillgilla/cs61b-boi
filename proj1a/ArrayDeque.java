/**
 * Created by Arjun on 1/31/2017.
 */
public class ArrayDeque<Item> {
    private Item[] array;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        this.array = (Item[]) new Object[8];
        this.size = 0;
        this.nextFirst = this.array.length / 2;
        this.nextLast = this.nextFirst + 1;
    }

    public void addLast(Item data) {
        this.array[this.nextLast] = data;
        this.nextLast++;
        if (this.nextLast >= this.array.length) {
            this.nextLast = 0;
        }
        this.size++;
        if (this.size >= this.array.length) {
            this.resizeUp();
        }
    }

    public void addFirst(Item data) {
        this.array[this.nextFirst] = data;
        this.nextFirst--;
        if (this.nextFirst < 0) {
            this.nextFirst = this.array.length - 1;
        }
        this.size++;
        if (this.size >= this.array.length) {
            this.resizeUp();
        }
    }

    public Item removeLast() {
        if (this.size == 0) {
            return null;
        } else {
            Item temp = this.get(this.size - 1);
            this.nextLast--;
            if (this.nextLast < 0) {
                this.nextLast = this.array.length - 1;
            }
            this.array[this.nextLast] = null;
            this.size--;
            if (this.array.length >= 16
                    && ((double) this.size / (double) this.array.length) < .25) {
                resizeDown();
            }
            return temp;
        }
    }

    public Item removeFirst() {
        if (this.size == 0) {
            return null;
        } else {
            Item temp = this.get(0);
            this.nextFirst++;
            if (this.nextFirst >= this.array.length) {
                this.nextFirst = 0;
            }
            this.array[this.nextFirst] = null;
            this.size--;
            if (this.array.length >= 16
                    && ((double) this.size / (double) this.array.length) < .25) {
                resizeDown();
            }
            return temp;
        }
    }

    public Item get(int index) {
        if (index >= this.size) {
            return null;
        }
        if ((this.nextFirst + 1 + index) >= this.array.length) {
            return this.array[this.nextFirst + 1 + index - this.array.length];
        } else {
            return this.array[this.nextFirst + 1 + index];
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int n = 0; n < this.size; n++) {
            System.out.print(this.get(n) + " ");
        }
    }

    private void resizeUp() {
        Item[] newArray = (Item[]) new Object[this.array.length * 2];
        if ((this.array.length - (this.nextFirst + 1)) < this.size) {
            System.arraycopy(this.array,
                      this.nextFirst + 1,
                             newArray,
                             (int) ((newArray.length / 2) - this.array.length / 2),
                      this.array.length - (this.nextFirst + 1));
            System.arraycopy(this.array,
                      0,
                             newArray,
                    (int) ((newArray.length / 2) - this.array.length / 2)
                                                                + this.array.length
                                                                - (this.nextFirst + 1),
                     this.size - (this.array.length - (this.nextFirst + 1)));
        } else {
            System.arraycopy(this.array,
                      this.nextFirst + 1,
                             newArray,
                             (int) ((newArray.length / 2) - this.array.length / 2),
                             this.size);
        }

        this.nextFirst = (int) ((newArray.length / 2) - this.array.length / 2) - 1;
        this.nextLast = nextFirst + this.size + 1;
        this.array = newArray;
    }

    private void resizeDown() {
        Item[] newArray = (Item[]) new Object[(int) (this.array.length / 2)];
        System.arraycopy(this.array,
                  this.nextFirst + 1,
                         newArray,
                         (int) ((newArray.length / 2) - this.size / 2),
                         this.size);
        this.array = newArray;
        this.nextFirst = (int) ((newArray.length / 2) - this.size / 2) - 1;
        this.nextLast = this.nextFirst + this.size + 1;
    }

    private static void printArray(Object[] array) {
        for (Object object : array) {
            System.out.print(object + " ");
        }
        System.out.println("");
    }

}
