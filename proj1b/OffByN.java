/**
 * Created by Arjun on 2/7/2017.
 */
public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int n) {
        this.n = n;
    }

    @Override
    public boolean equalChars(char a, char b) {
        if (Math.abs(a - b) == n) {
            return true;
        }
        return false;
    }

}
