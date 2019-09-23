import java.util.Arrays;

public class FallingBubbles extends UnionFind {
    private int[][]  bubblegrid;
    private int[][]  darts;
    private int row;
    private int column;
    private int[] size;

    FallingBubbles(int[][] bg,int[][] d){
        super(bg.length * bg[0].length);
        bubblegrid = bg;
        darts = d;
        row = bubblegrid.length;
        column = bubblegrid[0].length;
        size = new int[row*column];
        Arrays.fill(size,0);

    }

    @Override
    public int sizeOf(int v1) {
        return size[v1];
    }

    private int[] isNeigborStuck(int i, int j){
        int[]  ans = new int[2];
        if(i - 1 >= 0){
            if (belongsto[find((i - 1)*row + j)] == -1) {
                ans[0] = i - 1;
                ans[1] = j;
                return ans;
            }
        }
        if(j - 1 >= 0) {
            if (belongsto[find(i * row + (j - 1))] == -1) {
                ans[0] = i;
                ans[1] = j - 1;
                return ans;
            }
        }
        if(i + 1 < bubblegrid.length) {
            if (belongsto[find((i + 1) * row + j)]  == -1) {
                ans[0] = i + 1;
                ans[1] = j;
                return ans;
            }
        }
        if(j + 1 < bubblegrid[0].length) {
            if (belongsto[find(i  * row + (j + 1))] == -1) {
                ans[0] = i;
                ans[1] = j + 1;
                return ans;
            }
        }

        return null;
    }

    public void bubbleInit(){
        if(bubblegrid.length == 0) return;
        Arrays.fill(belongsto,-row*column);
        for (int i = 0; i < bubblegrid[0].length; i ++) {
            if (bubblegrid[0][i] == 1) belongsto[i * row] = -1;
        }
        for (int i = 0; i < bubblegrid[0].length; i ++){
            for (int j = 1; j < bubblegrid.length; j++){
                if(bubblegrid[j][i] == 0) continue;
                if(isNeigborStuck(i,j) != null){
                    int[] ij = isNeigborStuck(i,j);
                    unitUnion(i*row+j,ij[0]*row + ij[1]);
                    int temp = ij[0]*row + ij[1];
                    while (parent(temp) >= -1){
                        size[temp] ++;
                        if(parent(temp) < 0)  break;
                        temp = parent(temp);
                    }
                }
            }
        }
    }

    public int[] bubblePop(){
        int[] fallBubbleNum = new int[darts.length];
        for (int i = 0; i < darts.length; i ++){
            if(bubblegrid[darts[i][0]][darts[i][1]] == 0) {
                fallBubbleNum[i] =0;
            }else {
                fallBubbleNum[i] = size[darts[i][1]*row+darts[i][0]];

            }

        }
        return fallBubbleNum;
    }

    public static void main(String[] args) {
        int[][] gb = {{1, 1, 0}, {1, 0, 0}, {1, 1, 0}, {1, 1, 1}};
        int[][] dt = {{2, 2}, {2, 0}};
        int[]  ans;

        FallingBubbles fb = new FallingBubbles(gb, dt);
        fb.bubbleInit();
        ans = fb.bubblePop();
    }

}
