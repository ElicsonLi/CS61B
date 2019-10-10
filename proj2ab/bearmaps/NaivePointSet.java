package bearmaps;

import java.util.HashSet;
import java.util.List;

public class NaivePointSet implements PointSet {
    HashSet<Point> hs = new HashSet<>();

    public NaivePointSet(List<Point> points){
         for(Point x : points){
             hs.add(x);
         }
    }
    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x,y);
        double distance = Double.POSITIVE_INFINITY;
        Point best = null;
        for(Point p : hs){
            if(Point.distance(p,goal) < distance){
                best = p;
                distance =  Point.distance(p,goal);
            }
        }
        return best;
    }


//    public static void main(String[] args) {
//        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
//        Point p2 = new Point(3.3, 4.4);
//        Point p3 = new Point(-2.9, 4.2);
//
//        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
//        Point ret = nn.nearest(3.0, 4.0); // returns p2
//        ret.getX(); // evaluates to 3.3
//        ret.getY();
//    }
}
