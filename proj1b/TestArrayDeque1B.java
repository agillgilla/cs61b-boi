/**
 * Created by Arjun on 2/5/2017.
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {

    @Test
    public void testRandomArrayDequeMethods() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        int size = 0;
        String log = "";
        while (true) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            Integer randomData = (int) Math.round(StdRandom.uniform() * 10);

            if (numberBetweenZeroAndOne < 0.25) {
                log += "addLast(" + randomData + ")" + "\n";
                sad1.addLast(randomData);
                ads1.addLast(randomData);
                size += 1;
            } else if (numberBetweenZeroAndOne < .5) {
                log += "addFirst(" + randomData + ")" + "\n";
                sad1.addFirst(randomData);
                ads1.addFirst(randomData);
                size += 1;
            } else if (numberBetweenZeroAndOne < .75) {
                if (size > 0) {
                    log += "removeFirst()" + "\n";
                    assertEquals(log, sad1.removeFirst(), ads1.removeFirst());
                    size -= 1;
                }
            } else {
                if (size > 0) {
                    log += "removeLast()" + "\n";
                    assertEquals(log, sad1.removeLast(), ads1.removeLast());
                    size -= 1;
                }
            }
        }
    }
}
