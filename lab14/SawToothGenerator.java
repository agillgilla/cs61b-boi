import lab14lib.Generator;

/**
 * Created by Arjun on 4/28/2017.
 */
public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        this.state = 0;
        this.period = period;
    }

    public double next() {
        this.state += 1;
        return normalize();
    }

    public double normalize() {
        int unNormal = this.state % this.period;
        return ((2.0 / (this.period - 1)) * unNormal) - 1;
    }
}
