package hw4.hash;

import java.util.List;
import java.util.ArrayList;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO: Write a utility function that returns true if the given oomages 
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        int[] bucketFillCounts = new int[M];
        for (Oomage oomage : oomages) {
            int bucketNum = (oomage.hashCode() & 0x7FFFFFFF) % M;
            bucketFillCounts[bucketNum] = bucketFillCounts[bucketNum] + 1;
        }
        for (int numOomages : bucketFillCounts) {
            if (numOomages < (N / 50.0) || numOomages > (N / 2.5)) {
                return false;
            }
        }
        return true;
    }
}
