package hw3.hash;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        /* After getting your simpleOomages to spread out
           nicely, be sure to try
           scale = 0.5, N = 2000, M = 100. */

        double scale = 1.0;
        int N = 100;
        int M = 10;


        HashTableDrawingUtility.setScale(scale);
        List<Oomage> oomies = new ArrayList<>();
        for (int i = 0; i < N; i += 1) {
//            oomies.add(SimpleOomage.randomSimpleOomage());
            oomies.add(ComplexOomage.randomComplexOomage());
        }
        visualize(oomies, M, scale);

        /**  Test ComplexOomage's hashcode have flaw
         */
        /*
        List<Oomage> deadlyList = new ArrayList<>();
        LinkedList<Integer> params = new LinkedList<>();
        for (int i = 0; i < 6; i += 1) {
            params.addLast(StdRandom.uniform(0, 255));
        }
        for (int i = 0; i < N; i++){
            params.removeFirst();
            params.addFirst(StdRandom.uniform(0, 255));
            deadlyList.add(new ComplexOomage(params));
        }
        visualize(deadlyList, M, scale);
        */
    }

    public static void visualize(List<Oomage> oomages, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);
        int[] numInBucket = new int[M];
        for (Oomage s : oomages) {
            int bucketNumber = (s.hashCode() & 0x7FFFFFFF) % M;
            double x = HashTableDrawingUtility.xCoord(numInBucket[bucketNumber]);
            numInBucket[bucketNumber] += 1;
            double y = HashTableDrawingUtility.yCoord(bucketNumber, M);
            s.draw(x, y, scale);
        }
    }
} 
