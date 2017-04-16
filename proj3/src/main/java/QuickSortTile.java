/**
 * Created by Arjun on 4/13/2017.
 */
public class QuickSortTile {

    private Tile[] array;
    private int length;

    public void sortIncreasing(Tile[] inputArr, String attr) {

        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSortIncreasing(0, length - 1, attr);
    }

    private void quickSortIncreasing(int lowerIndex, int higherIndex, String attr) {

        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        Tile pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i].attrLessThan(pivot, attr)) { // CHANGE < to > for greatest to least sort
                i++;
            }
            while (array[j].attrGreaterThan(pivot, attr)) { // CHANGE > to < for greatest to least sort
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSortIncreasing(lowerIndex, j, attr);
        if (i < higherIndex)
            quickSortIncreasing(i, higherIndex, attr);
    }

    public void sortDecreasing(Tile[] inputArr, String attr) {

        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSortDecreasing(0, length - 1, attr);
    }

    private void quickSortDecreasing(int lowerIndex, int higherIndex, String attr) {

        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        Tile pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i].attrGreaterThan(pivot, attr)) { // CHANGE > to < for least to greatest sort
                i++;
            }
            while (array[j].attrLessThan(pivot, attr)) { // CHANGE < to > for least to greatest sort
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSortDecreasing(lowerIndex, j, attr);
        if (i < higherIndex)
            quickSortDecreasing(i, higherIndex, attr);
    }

    private void exchangeNumbers(int i, int j) {
        Tile temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
