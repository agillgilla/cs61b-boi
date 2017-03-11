package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceHigh;

    /**
     * perform T independent experiments on an N x N grid
     * @param N Dimension of grid
     * @param T Number of experiments
     */
    public PercolationStats(int N, int T) {

        double[] opensList = new double[T];
        double currMeanSum = 0;
        for (int test = 0; test < T; test++) {
            Percolation p = new Percolation(N);
            int opens = 0;
            int row;
            int col;
            while (!p.percolates()) {
                row = StdRandom.uniform(N);
                col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    opens++;
                }
            }
            opensList[test] = opens / Math.pow(N, 2);
            currMeanSum += opens / Math.pow(N, 2);
        }
        this.mean = currMeanSum / T;

        double stdevSum = 0;
        for (double numOpens : opensList) {
            stdevSum += Math.pow(numOpens - this.mean, 2);
        }
        this.stddev = Math.pow(stdevSum / (T - 1), .5);

        this.confidenceLow = this.mean - (1.96 * this.stddev) / Math.pow(T, .5);
        this.confidenceHigh = this.mean + (1.96 * this.stddev) / Math.pow(T, .5);
    }

    /**
     *
     * @return sample mean of population threshold
     */
    public double mean() {
        return this.mean;
    }

    /**
     *
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return this.stddev;
    }

    /**
     *
     * @return low endpoint of 95% confidence interval
     */
    public double confidenceLow() {
        return this.confidenceLow;
    }

    /**
     *
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHigh() {
        return this.confidenceHigh;
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(20, 1000);
        System.out.println(ps.mean());
    }
}                       
