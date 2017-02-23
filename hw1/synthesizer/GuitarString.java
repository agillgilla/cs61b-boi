
package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {


        this.buffer = new ArrayRingBuffer<Double>((int) Math.round(this.SR / frequency));
        for (int i = 0; i < this.buffer.capacity(); i++) {
            this.buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        while (this.buffer.fillCount() != 0) {
            this.buffer.dequeue();
        }
        System.out.println(this.buffer.fillCount());
        while (this.buffer.fillCount() < this.buffer.capacity()) {
            this.buffer.enqueue(Math.random() - .5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        double newSample = this.buffer.dequeue();
        newSample = ((newSample + this.buffer.peek()) / 2) * this.DECAY;
        this.buffer.enqueue(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {

        return this.buffer.peek();
    }
}
