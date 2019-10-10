package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void test(){
        for(int times = 0;times < 100; times++){
            List<Point> points = new ArrayList<>();
            for(int i = 0; i < 100000; i++){
                double x = StdRandom.uniform();
                double y = StdRandom.uniform();
                Point p = new Point(x,y);
                points.add(p);
            }
            double goalx = StdRandom.uniform();
            double goaly = StdRandom.uniform();

//            double goalx = 0.05;
//            double goaly = 0.26;
//
//            Point p1 = new Point(0.96,0.84);
//            Point p2 = new Point(0.15,0.91);
//            Point p3 = new Point(0.53,0.68);
//            Point p4 = new Point(0.65,0.54);
//            Point p5 = new Point(0.67,0.37);
//
//
//            List<Point> points = List.of(p1,p2,p3,p4,p5);

            Point goal =new Point(goalx,goaly);
            NaivePointSet nps = new NaivePointSet(points);
            KDTree kd = new KDTree(points);

            long start = System.currentTimeMillis();
            Point ansnps = nps.nearest(goalx,goaly);
            long end = System.currentTimeMillis();
            double time1 = (end - start)/1000.0;

            long start2 = System.currentTimeMillis();
            Point anskd = kd.nearest(goalx,goaly);
            long end2 = System.currentTimeMillis();
            double time2 = (end2 - start2)/1000.0;

            assertEquals(ansnps,anskd);
        }

    }

}
