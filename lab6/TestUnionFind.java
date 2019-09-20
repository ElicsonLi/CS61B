import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void Test(){
        UnionFind uf = new UnionFind(10);
        uf.union(1,2);
        uf.union(3,2);
        uf.union(4,2);
        uf.union(5,6);
        uf.union(7,8);
        uf.union(6,9);

        assertTrue(uf.connected(2,3));
        assertTrue(uf.connected(6,9));
        assertFalse(uf.connected(2,6));

        assertEquals(uf.sizeOf(4),4);
        assertEquals(uf.find(9),5);

        uf.union(3,6);
        assertEquals(uf.sizeOf(9),7);

    }
}
