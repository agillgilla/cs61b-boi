package hw2;                       


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final boolean OPEN = true;
    private static final boolean BLOCKED = false;
    private WeightedQuickUnionUF unionHelper;
    private WeightedQuickUnionUF topUnionHelper;
    private int N;
    private boolean[][] sites;
    private int vTop, vBottom;
    private int numOpen;

    /**
     * Creates an N x N grid, with all sites initially blocked
     *
     * @param N Width/height of grid
     */
    public Percolation(int N)  {
        if (N <= 0) {
            throw new IllegalArgumentException(
                    "Dimension of Percolation grid must be greater than 0.");
        }

        this.N = N;
        this.unionHelper = new WeightedQuickUnionUF((int) (Math.pow(N, 2) + 2));
        this.topUnionHelper = new WeightedQuickUnionUF((int) (Math.pow(N, 2) + 1));
        this.sites = new boolean[N][N];
        this.vTop = (int) Math.pow(N, 2);
        this.vBottom = (int) (Math.pow(N, 2) + 1);
        this.numOpen = 0;

        // Fill site 2D array with blocked sites
        for (int r = 0; r < this.N; r++) {
            for (int c = 0; c < this.N; c++) {
                this.sites[r][c] = this.BLOCKED;
            }
        }

        // Link top and bottom rows to virtual top/bottom sites
        for (int c = 0; c < this.N; c++) {
            int index = this.xyTo1D(0, c);
            this.unionHelper.union(this.vTop, index);
            this.topUnionHelper.union(this.vTop, index);
            index = this.xyTo1D(this.N - 1, c);
            this.unionHelper.union(this.vBottom, index);
        }
    }

    /**
     * Open the site (row, col) if it is not open already
     *
     * @param row Row index to open site
     * @param col Column index to open site
     */
    public void open(int row, int col)  {
        if (!this.validIndex(row, col)) {
            throw new IndexOutOfBoundsException(
                    "Site '(" + row + ", " + col + "') is out of bounds!");
        }

        if (!this.isOpen(row, col)) {
            this.sites[row][col] = this.OPEN;
            int index = this.xyTo1D(row, col);
            if (validIndex(row - 1, col)) {
                if (isOpen(row - 1, col)) {
                    this.unionHelper.union(index, this.xyTo1D(row - 1, col));
                    this.topUnionHelper.union(index, this.xyTo1D(row - 1, col));
                }
            }
            if (validIndex(row + 1, col)) {
                if (isOpen(row + 1, col)) {
                    this.unionHelper.union(index, this.xyTo1D(row + 1, col));
                    this.topUnionHelper.union(index, this.xyTo1D(row + 1, col));
                }
            }
            if (validIndex(row, col - 1)) {
                if (isOpen(row, col - 1)) {
                    this.unionHelper.union(index, this.xyTo1D(row, col - 1));
                    this.topUnionHelper.union(index, this.xyTo1D(row, col - 1));
                }
            }
            if (validIndex(row, col + 1)) {
                if (isOpen(row, col + 1)) {
                    this.unionHelper.union(index, this.xyTo1D(row, col + 1));
                    this.topUnionHelper.union(index, this.xyTo1D(row, col + 1));
                }
            }
            this.numOpen++;
        }
    }

    /**
     * Is the site (row, col) open?
     * @param row Row index of site
     * @param col Column index of site
     * @return (row, col) open site?
     */
    public boolean isOpen(int row, int col)  {
        if (!this.validIndex(row, col)) {
            throw new IndexOutOfBoundsException(
                    "Site '(" + row + ", " + col + "') is out of bounds!");
        }
        return this.sites[row][col] == this.OPEN;
    }

    /**
     * Is the site (row, col) full?
     * @param row Row index of site
     * @param col Column index of site
     * @return (row, col) open full?
     */
    public boolean isFull(int row, int col) {
        if (!this.validIndex(row, col)) {
            throw new IndexOutOfBoundsException(
                    "Site '(" + row + ", " + col + "') is out of bounds!");
        }
        return this.topUnionHelper.connected(this.vTop, this.xyTo1D(row, col)) && isOpen(row, col);
    }

    /**
     * How many sites are open?
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return this.numOpen;
    }

    /**
     * Does the system percolate?
     * @return system percolates?
     */
    public boolean percolates() {
        return this.unionHelper.connected(this.vTop, this.vBottom)
                && (this.numberOfOpenSites() > 0);
    }

    public static void main(String[] args) { // unit testing

    }

    private int xyTo1D(int x, int y) {
        return x * this.N + y;
    }

    private boolean validIndex(int row, int col) {
        return (row >= 0 && row < this.N) && (col >= 0 && col < this.N);
    }
}                       
