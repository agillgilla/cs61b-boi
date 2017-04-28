import lab14lib.Generator;

/**
 * Created by Arjun on 4/28/2017.
 */
public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double accelFactor;

    public AcceleratingSawToothGenerator(int period, double accelFactor) {
        this.state = 0;
        this.period = period;
        this.accelFactor = accelFactor;
    }

    public double next() {
        this.state += 1;

        if (state == period - 1) {
            System.out.println("OLD PERIOD: " + this.period);
            this.period = (int) (this.period * this.accelFactor);
            System.out.println("NEW PERIOD: " + this.period);
            state = 0;
        }

        return normalize();
    }

    public double normalize() {
        int unNormal = this.state;
        return ((2.0 / (this.period - 1)) * unNormal) - 1;
    }
}
