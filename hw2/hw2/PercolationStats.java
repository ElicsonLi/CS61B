package hw2;

import edu.princeton.cs.algs4.StdRandom;
import  edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] fractor;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if(N <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
        fractor = new double[T];
        for (int i = 0; i < T; i++){
            Percolation p = pf.make(N);
            while (!p.percolates()){
                int x = StdRandom.uniform(0,10);
                int y = StdRandom.uniform(0,10);
                p.open(x,y);
            }
            fractor[i] = p.numberOfOpenSites()/N*N;
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(fractor);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractor);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        return mean()-1.96*stddev()/Math.pow(fractor.length,0.5);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return mean()+1.96*stddev()/Math.pow(fractor.length,0.5);
    }
}

