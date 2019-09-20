import org.junit.Test;

public class WQUwithPathCompresion extends UnionFind {
    public WQUwithPathCompresion(int n) {
        super(n);
    }

    @Override
    public boolean connected(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        int v1t = v1;
        int v2t = v2;
        while (parent(v1t) != r1){
            unitUnion(v1t,r1);
            v1t = parent(v1t);
        }
        while (parent(v2t) != r2){
            unitUnion(v2t,r2);
        }
        return r1 == r2;
    }

}
