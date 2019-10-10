package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static bearmaps.PrintHeapDemo.printSimpleHeapDrawing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {
    @Test
    public void addTest(){
        ArrayHeapMinPQ<Integer> ahm = new ArrayHeapMinPQ<>();
        ahm.add(10,10);
        ahm.add(1,1);
        ahm.add(9,9);
        ahm.add(7,7);
        ahm.add(13,13);
        ahm.add(5,5);
        ahm.add(2,2);
        ahm.add(16,16);
        ahm.add(19,19);
        //ahm.changePriority(19,0);
        ahm.removeSmallest();
        System.out.println();
    }

    @Test
    public void timeTest(){
        ArrayHeapMinPQ<Double> ahm = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Double> nm = new NaiveMinPQ<>();
        for(int i = 0; i < 10000; i ++){
            double x = StdRandom.uniform();
            ahm.add(x,x);
            nm.add(x,x);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i += 1) {
            ahm.removeSmallest();
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i += 1) {
            nm.removeSmallest();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2)/1000.0 +  " seconds.");
    }
}
