/**
 * Created by Arjun on 2/1/2017.
 */
public class Sort {

    /** Sorts string destructively. */
    public static void sort(String[] x) {
        Sort.sort(x, 0);
    }

    /** Sorts x starting at position k. */
    public static void sort(String[] x, int k) {
        if (k == x.length) {
            return;
        }
        int smallestIndex = findSmallest(x, k);
        swap(x, 0, smallestIndex);
        sort(x, k + 1);
    }

    public static void swap(String[] x, int a, int b) {
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }

    /** Return the smallest string starting at index k. */
    public static int findSmallest(String[] x, int k) {
        int smallestIndex = k;
        int i = k;
        while (i < x.length) {
            if (x[i].compareTo(x[smallestIndex]) < 0) {
                smallestIndex = i;
            }
            i++;
        }
        return smallestIndex;
    }
}
