/**
 * Created by Arjun on 1/31/2017.
 */
public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        this.array = (T[])new Object[8];
        this.size = 0;
        this.nextFirst = this.array.length / 2;
        this.nextLast = this.nextFirst + 1;
    }

    public void addLast(T data) {
        this.array[this.nextLast] = data;
        this.nextLast++;
        if (this.nextLast >= this.array.length) {
            this.nextLast = 0;
        }
        this.size++;
        if (this.size >= this.array.length) {
            this.resize();
        }
    }

    public void addFirst(T data) {
        this.array[this.nextFirst] = data;
        this.nextFirst--;
        if (this.nextFirst < 0) {
            this.nextFirst = this.array.length - 1;
        }
        this.size++;
        if (this.size >= this.array.length) {
            this.resize();
        }
    }

    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        else {
            T temp = this.get(this.size - 1);
            this.nextLast--;
            if (this.nextLast < 0) {
                this.nextLast = this.array.length - 1;
            }
            //this.array[this.nextLast] = null;
            this.size--;
            return temp;
        }
    }

    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        else {
            T temp = this.get(0);
            this.nextFirst++;
            if (this.nextFirst >= this.array.length) {
                this.nextFirst = 0;
            }
            //this.array[this.nextFirst] = null;
            this.size--;
            return temp;
        }
    }

    public T get(int index) {
        if (index >= this.size) {
            return null;
        }
        if ((this.nextFirst + 1 + index) >= this.array.length) {
            return this.array[this.nextFirst + 1 + index - this.array.length];
        }
        else {
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

    private void resize() {
        T[] newArray = (T[]) new Object[this.array.length * 2];
        if ((this.array.length - (this.nextFirst + 1)) < this.size) {
            System.arraycopy(this.array, this.nextFirst + 1, newArray, (int)((newArray.length / 2) - this.array.length / 2), this.array.length - (this.nextFirst + 1));
            System.arraycopy(this.array, 0, newArray, (int)((newArray.length / 2) - this.array.length / 2) + this.array.length - (this.nextFirst + 1), this.size - (this.array.length - (this.nextFirst + 1)));
        }
        else {
            System.arraycopy(this.array, this.nextFirst + 1, newArray, (int)((newArray.length / 2) - this.array.length / 2), this.size);
        }

        this.nextFirst = (int)((newArray.length / 2) - this.array.length / 2) - 1;
        this.nextLast = nextFirst + this.size + 1;

        this.array = newArray;
    }

    public static void printArray(Object[] array) {
        for (Object object : array) {
            System.out.print(object + " ");
        }
    }

}
