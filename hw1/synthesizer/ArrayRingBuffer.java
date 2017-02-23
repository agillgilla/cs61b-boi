// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
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
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
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
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
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
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
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
        // TODO: Return the first item. None of your instance variables should change.
        if (this.fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return this.rb[this.first];
        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    private class RingBufferIterator implements Iterator<T> {
        private ArrayRingBuffer<T> buffer;
        private int currIndex;

        public RingBufferIterator(ArrayRingBuffer buffer) {
            this.buffer = buffer;
            this.currIndex = first;
        }

        public boolean hasNext() {
            if ((this.currIndex + 1) % buffer.capacity() == buffer.last) {
                return false;
            }
            else {
                return true;
            }
        }

        public T next() {
            if (this.hasNext()) {
                this.currIndex += 1;
                if (this.currIndex > buffer.capacity) {
                    this.currIndex = 0;
                }
                return buffer.rb[currIndex];
            }
            else {
                return null;
            }
        }
    }

    public Iterator<T> iterator() {
        return new RingBufferIterator(this);

    }
}
