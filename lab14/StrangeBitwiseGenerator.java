import lab14lib.Generator;

/**
 * Created by Arjun on 4/28/2017.
 */
public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        this.state = 0;
        this.period = period;
    }

    public double next() {
        this.state += 1;
        int weirdState = state & (state >> 7) % period;
        return normalize(weirdState);
    }

    public double normalize(int state) {
        int unNormal = state % this.period;
        return ((2.0 / (this.period - 1)) * unNormal) - 1;
    }
}
