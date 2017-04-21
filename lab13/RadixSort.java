import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra
 * @version 1.4 - April 14, 2016
 *
 **/
public class RadixSort
{

    /**
     * Does Radix sort on the passed in array with the following restrictions:
     *  The array can only have ASCII Strings (sequence of 1 byte characters)
     *  The sorting is stable and non-destructive
     *  The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     **/
    public static String[] sort(String[] asciis)
    {
        if (asciis == null || asciis.length == 0) {
            return asciis;
        }
        String[] asciisCopy = asciis.clone();
        quickSort(asciisCopy,0, asciisCopy.length - 1);
        return asciisCopy;
    }

    /**
     * Radix sort helper function that recursively calls itself to achieve the sorted array
     *  destructive method that changes the passed in array, asciis
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelper(String[] asciis, int start, int end, int index)
    {
        //TODO use if you want to
    }



    private static void quickSort(String[] inputArr, int lowerIndex, int higherIndex) {

        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        String pivot = inputArr[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (inputArr[i].compareTo(pivot) < 0) {
                i++;
            }
            while (inputArr[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                exchangeValues(inputArr, i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(inputArr, lowerIndex, j);
        if (i < higherIndex)
            quickSort(inputArr, i, higherIndex);
    }

    private static void exchangeValues(String[] inputArr, int i, int j) {
        String temp = inputArr[i];
        inputArr[i] = inputArr[j];
        inputArr[j] = temp;
    }
}
