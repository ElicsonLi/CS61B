package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void enqueueTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.dequeue();
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(0);
        arb.enqueue(-1);

        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer(10);
        arb2.enqueue(-1);
        arb2.enqueue(2);
        arb2.enqueue(3);
        arb2.enqueue(4);
        arb2.enqueue(5);
        arb2.enqueue(6);
        arb2.enqueue(7);
        arb2.enqueue(8);
        arb2.enqueue(9);
        arb2.enqueue(0);

        assertTrue(arb.equals(arb2));

    }
}
