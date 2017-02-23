
package synthesizer;

import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {

        super();
        this.rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {

        if (this.fillCount == this.capacity) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            this.rb[last] = x;
            this.last++;
            if (this.last == this.rb.length) {
                this.last = 0;
            }
            this.fillCount++;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {

        if (this.fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T temp = this.rb[this.first];
            this.first++;
            if (this.first == this.rb.length) {
                this.first = 0;
            }
            this.fillCount--;
            return temp;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {

        if (this.fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return this.rb[this.first];
        }
    }


    private class RingBufferIterator implements Iterator<T> {
        private ArrayRingBuffer<T> buffer;
        private int currIndex;

        RingBufferIterator(ArrayRingBuffer buffer) {
            this.buffer = buffer;
            this.currIndex = first;
        }

        public boolean hasNext() {
            if ((this.currIndex + 1) % buffer.capacity() == buffer.last) {
                return false;
            }
            return true;
        }

        public T next() {
            if (this.hasNext()) {
                this.currIndex += 1;
                if (this.currIndex > buffer.capacity) {
                    this.currIndex = 0;
                }
                return buffer.rb[currIndex];
            } else {
                return null;
            }
        }
    }

    public Iterator<T> iterator() {
        return new RingBufferIterator(this);

    }
}
