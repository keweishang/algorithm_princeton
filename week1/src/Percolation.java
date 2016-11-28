import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // Quick Union data structure that store the components
    private WeightedQuickUnionUF qu;
    private WeightedQuickUnionUF backwash;
    private boolean[] open;
    private int n;

    // create n-by-n grid, with all sites blocked
    // Space : O(n)
    // Time : O(n^2)
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n or trails is less or equlas to 0");
        this.n = n;
        // 2 extra elements: 0 connects to the 1st row, n * n + 1 connects to
        // the last row
        qu = new WeightedQuickUnionUF(n * n + 2);
        open = new boolean[n * n + 2];

        // fix: backwash
        backwash = new WeightedQuickUnionUF(n * n + 1);
    }

    // open site (row, col) if it is not open already
    // time: lg(m), where m is the total number of grid in matrix
    public void open(int row, int col) {
        validate(row, col);
        int idx = to1d(row, col);
        open[idx] = true;

        // union neighbours
        for (int[] dir : DIRS) {
            int r = row + dir[0];
            int c = col + dir[1];
            if (r < 1) {
                qu.union(0, idx);
                backwash.union(0, idx);
            } else if (r > n) {
                qu.union(n * n + 1, idx);
            } else if (c >= 1 && c <= n) {
                int neighbourIdx = to1d(r, c);
                if (open[neighbourIdx]) {
                    qu.union(neighbourIdx, idx);
                    backwash.union(neighbourIdx, idx);
                }
            }
        }
    }

    // is site (row, col) open?
    // time : O(1)
    public boolean isOpen(int row, int col) {
        validate(row, col);
        int idx = to1d(row, col);
        return open[idx];
    }

    // is site (row, col) full?
    // time : lg(m), where m is the total number of grid in matrix
    public boolean isFull(int row, int col) {
        validate(row, col);
        return backwash.connected(0, to1d(row, col));
    }

    // does the system percolate?
    // time : lg(m), where m is the total number of grid in matrix
    public boolean percolates() {
        // trick: check if 2 special elements are connected
        return qu.connected(0, n * n + 1);
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IndexOutOfBoundsException("row and col value should between 1 and n");
    }

    private int to1d(int row, int col) {
        return (row - 1) * n + col;
    }
}