/**
 * Created by Arjun on 2/1/2017.
 */

import org.junit.Assert;

public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }    
    
    /* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct, 
     * finally printing the results. 
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.addLast("middle");
        passed = checkSize(2, ad1.size()) && passed;

        ad1.addLast("back");
        passed = checkSize(3, ad1.size()) && passed;

        System.out.println("Printing out deque: ");
        ad1.printDeque();

        printTestStatus(passed);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        // should be empty 
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty 
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.removeFirst();
        // should be empty 
        passed = checkEmpty(true, ad1.isEmpty()) && passed;

        printTestStatus(passed);

    }

    /** Tests all features of the Deque API. */
    public static void multiTest() {

        System.out.println("Running exhaustive test.");

        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        // should be empty
        Assert.assertEquals(true, ad1.isEmpty());

        // should have size 0
        Assert.assertEquals(0, ad1.size());

        ad1.addLast("a");

        // should not be empty
        Assert.assertEquals(false, ad1.isEmpty());

        ad1.addLast("b");

        // should have size 2
        Assert.assertEquals(2, ad1.size());

        ad1.addFirst("c");
        ad1.addLast("d");

        // should be null at index 4
        Assert.assertEquals(null, ad1.get(4));

        ad1.addLast("e");

        // should have following structure [c, a, b, d, e]
        //                                  |  |  |  |  |
        //                                  0  1  2  3  4
        Assert.assertEquals("c", ad1.get(0));
        Assert.assertEquals("a", ad1.get(1));
        Assert.assertEquals("b", ad1.get(2));
        Assert.assertEquals("d", ad1.get(3));
        Assert.assertEquals("e", ad1.get(4));

        // should have last element "e"
        Assert.assertEquals("e", ad1.removeLast());

        // should have size 4
        Assert.assertEquals(4, ad1.size());

        //should have first element "c"
        Assert.assertEquals("c", ad1.removeFirst());

        //should have size 3
        Assert.assertEquals(3, ad1.size());

        ad1.addFirst("c");
        ad1.addLast("e");

        //should have size 5
        Assert.assertEquals(5, ad1.size());

        // should be back to following structure [c, a, b, d, e]
        //                                        |  |  |  |  |
        //                                        0  1  2  3  4
        Assert.assertEquals("c", ad1.get(0));
        Assert.assertEquals("a", ad1.get(1));
        Assert.assertEquals("b", ad1.get(2));
        Assert.assertEquals("d", ad1.get(3));
        Assert.assertEquals("e", ad1.get(4));

        // should not be empty
        Assert.assertEquals(false, ad1.isEmpty());

        ad1.addFirst("f");
        ad1.addLast("g");
        ad1.addFirst("h");
        ad1.addFirst("i");

        // should be the following structure [i, h, f, c, a, b, d, e, g]
        //                                    |  |  |  |  |  |  |  |  |
        //                                    0  1  2  3  4  5  6  7  8
        Assert.assertEquals("g", ad1.removeLast());
        Assert.assertEquals("e", ad1.removeLast());
        Assert.assertEquals("i", ad1.removeFirst());
        Assert.assertEquals("h", ad1.removeFirst());
        Assert.assertEquals("f", ad1.removeFirst());
        Assert.assertEquals("d", ad1.removeLast());

        // should be the following structure [c, a, b]
        //                                    |  |  |
        //                                    0  1  2
        // and should have resized down.

        System.out.println("");
        System.out.println("Finished exhaustive test.");
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        multiTest();
    }
}
