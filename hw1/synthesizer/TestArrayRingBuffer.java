package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testArrayRingBuffer() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        assertEquals(5, arb.capacity());
        arb.enqueue(1);
        arb.enqueue(2);
        assertEquals(2, arb.fillCount());
        assertEquals(Integer.valueOf(1), arb.dequeue());
        arb.enqueue(3);
        assertEquals(Integer.valueOf(2), arb.peek());
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        assertEquals(5, arb.fillCount());
        assertEquals(Integer.valueOf(2), arb.peek());
    }

    @Test(expected = RuntimeException.class)
    public void testArrayRingBufferOverflow() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(2);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
    }

    @Test(expected = RuntimeException.class)
    public void testArrayRingBufferUnderflow() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(2);
        arb.dequeue();
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
