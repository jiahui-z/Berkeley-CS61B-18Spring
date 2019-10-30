package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        this.capacity = capacity;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) throws RuntimeException {
        if (fillCount < capacity) {
            rb[last] = x;
            fillCount++;
            if (last < capacity - 1) {
                last++;
            } else {
                last = 0;
            }
        } else {
            throw new RuntimeException("Ring buffer overflow");
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() throws RuntimeException {
        if (fillCount > 0) {
            T removed = rb[first];
            rb[first] = null;
            fillCount--;
            if (first < capacity - 1) {
                first++;
            } else {
                first = 0;
            }
            return removed;
        } else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() throws RuntimeException {
        if (fillCount > 0) {
            return rb[first];
        } else {
            throw new RuntimeException("Ring buffer is empty");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T> {
        private int ptr;
        public BufferIterator() {
            ptr = 0;
        }
        public boolean hasNext() {
            return ptr != capacity - 1;
        }
        public T next() throws RuntimeException {
            if (ptr < capacity) {
                T returnItem = rb[ptr++];
                return returnItem;
            } else {
                throw new RuntimeException("Ring buffer has no next element");
            }
        }
    }
}
