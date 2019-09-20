public class UnionFind {

    private int [] belongsto;
    // TODO - Add instance variables?

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        belongsto = new int[n];
        for (int i = 0; i<belongsto.length;i++){
            belongsto[i] = -1;
        }
        // TODO
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex < 0 || vertex > belongsto.length-1){
            throw new  IllegalArgumentException("This is an invalidate index!");
        }
        // TODO
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int r = v1;
        while (belongsto[r] > 0){
            r = belongsto[r];
        }
        return -belongsto[r];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return belongsto[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        return r1 == r2;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        if (-belongsto[r1] < -belongsto[r2]){
            belongsto[r2] += belongsto[r1];
            belongsto[r1] = r2;
        }else {
            belongsto[r1] += belongsto[r2];
            belongsto[r2] = r1;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int r = vertex;
        while (belongsto[r] > 0){
            r = belongsto[r];
        }
        return r;
    }

}
