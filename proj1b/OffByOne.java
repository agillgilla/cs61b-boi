/**
 * Created by Arjun on 2/7/2017.
 */
public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char a, char b) {
        if (Math.abs(a - b) == 1) {
            return true;
        }
        return false;
    }
}
