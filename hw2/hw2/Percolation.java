package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {
    WeightedQuickUnionUF uf;
    private boolean[][] open;
    private int openSize = 0;
    private int TOP;
    private int BOTTOM;

    private void valid(int x){
        if (x < 0 || x >= open.length){
            throw new IndexOutOfBoundsException();
        }
    }
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        if (N <= 0){
            throw new IllegalArgumentException();
        }
        open = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N*N + 2);
        TOP = N*N ;
        BOTTOM = N*N + 1 ;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        valid(row);
        valid(col);
        if(isOpen(row,col)){
            return;
        }
        open[row][col] = true;
        openSize ++;
        if(row == 0) {
            uf.union(TOP,row*open.length + col);
        }
        if(row == open.length-1){
            uf.union(BOTTOM,row*open.length + col);
        }
        if(row - 1 >= 0){
            if(isOpen(row-1,col)){
                uf.union(row*open.length + col,(row-1)*open.length + col);
            }
        }
        if(row + 1 < open.length){
            if(isOpen(row+1,col)){
                uf.union(row*open.length + col,(row+1)*open.length + col);
            }
        }
        if(col - 1 >= 0){
            if(isOpen(row,col-1)){
                uf.union(row*open.length + col,row*open.length + col - 1);
            }
        }
        if(col + 1 < open[0].length){
            if(isOpen(row,col+1)){
                uf.union(row*open.length + col,row*open.length + col + 1);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        valid(row);
        valid(col);
        return open[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)  {
        valid(row);
        valid(col);
        return uf.connected(TOP,row * open.length + col);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSize;
    }
    // does the system percolate?
    public boolean percolates() {
        return uf.connected(TOP,BOTTOM);
    }

    // use for unit testing (not required, but keep this here for the autograder)

    public static void main(String[] args){
        Percolation p = new Percolation(10);
        while (p.numberOfOpenSites()<50){
            int x = StdRandom.uniform(0,10);
            int y = StdRandom.uniform(0,10);
            p.open(x,y);
        }
        System.out.println(p.percolates());
    }

}
