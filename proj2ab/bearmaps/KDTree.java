package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private class Node{
        Point p;
        boolean isLR = true;
        Node leftNode;
        Node rightNode;

        Node(Point po){
            p = po;
            leftNode = null;
            rightNode = null;
        }

        private void SetIsLR(boolean is){
            isLR = is;
        }


        void AddNode(Node root, Node nd){
            double rootValue;
            double ndValue;
            boolean isLR;
            if(root.isLR){
                rootValue = root.p.getX();
                ndValue = nd.p.getX();
                isLR = true;
            }else {
                rootValue = root.p.getY();
                ndValue = nd.p.getY();
                isLR = false;
            }
            if(ndValue > rootValue){
                if(root.rightNode == null){
                    root.rightNode = nd;
                    nd.SetIsLR(!isLR);
                    return;
                }else {
                    AddNode(root.rightNode,nd);
                }
            }else {
                if(root.leftNode == null){
                    root.leftNode = nd;
                    nd.SetIsLR(!isLR);
                    return;
                }else {
                    AddNode(root.leftNode,nd);
                }
            }

        }

    }

    Node first;
    Node nearest(Node nd, Point goal, Node best){
        if(nd == null) {
            return best;
        }

        Node goodSide;
        Node badSide;
        double partree;
        double pargoal;
        if(nd.isLR) {
            partree = nd.p.getX();
            pargoal = goal.getX();
        }else {
            partree = nd.p.getY();
            pargoal = goal.getY();
        }
        if(pargoal < partree){
            goodSide = nd.leftNode;
            badSide = nd.rightNode;
        }else {
            goodSide = nd.rightNode;
            badSide = nd.leftNode;
        }



        if(Point.distance(nd.p,goal) < Point.distance(best.p,goal)){
            best = nd;
        }

        best = nearest(goodSide,goal,best);
        if(Math.pow(pargoal - partree,2) < Point.distance(best.p,goal)){
            best = nearest(badSide,goal,best);
        }

        return best;
    }

    public KDTree(List<Point> points){
        if(points.size() == 0){
            throw new IllegalArgumentException();
        }
        first = new Node(points.get(0));
        for(int i = 1; i < points.size(); i++){
            Point x = points.get(i);
            Node nd = new Node(x);
            first.AddNode(first,nd);
        }
    }
    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x,y);
        return nearest(first,goal,first).p;
    }


//    public static void main(String[] args) {
//        Point p1 = new Point(0, 0); // constructs a Point with x = 1.1, y = 2.2
//        Point p2 = new Point(0.1, 7);
//        Point p3 = new Point(-1, 7);
//        Point p4 = new Point(-0.5, 5);
//
//
//        KDTree kd = new KDTree(List.of(p1, p2, p3,p4));
//        Point ret = kd.nearest(-1, 1); // returns p2
//        ret.getX(); // evaluates to 3.3
//        ret.getY();
//    }
}
