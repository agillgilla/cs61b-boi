/**
 * Created by Arjun on 2/1/2017.
 */
public class TestSort {

    //Tests Sort.sort
    public static void testSort() {
        String[] input = {"hello", "trump", "hug", "sandwich"};
        String[] expected = {"hello", "hug", "sandwich", "trump"};
        Sort.sort(input);

        org.junit.Assert.assertArrayEquals(expected, input);
    }

    public static void testFindSmallest() {
        String[] input = {"hello", "trump", "hug", "sandwich"};
        int expected = 2;
        int actual = Sort.findSmallest(input, 1);

        org.junit.Assert.assertEquals(expected, actual);
    }


    public static void main(String[] args) {
        testFindSmallest();
    }
}
