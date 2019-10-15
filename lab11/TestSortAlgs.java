import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
//        Queue<String> tas = new Queue<String>();
//        tas.enqueue("Joe");
//        tas.enqueue("Omar");
//        tas.enqueue("Itai");
//        tas.enqueue("Li");
//        tas.enqueue("Zheng");
//        tas.enqueue("Pei");
//        tas.enqueue("Ethan");
        Queue<Integer> tas = new Queue<>();
        tas.enqueue(1);
        tas.enqueue(32434);
        tas.enqueue(34);
        tas.enqueue(3546);
        tas.enqueue(746);
        tas.enqueue(42343);
        tas.enqueue(35);
        Queue<Integer> newtas = QuickSort.quickSort(tas);
        assertTrue(isSorted(newtas));
    }

    @Test
    public void testMergeSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        tas.enqueue("Li");
        tas.enqueue("Zheng");
        tas.enqueue("Pei");
        tas.enqueue("Ethan");
        Queue<String> newtas = MergeSort.mergeSort(tas);
        assertTrue(isSorted(newtas));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
