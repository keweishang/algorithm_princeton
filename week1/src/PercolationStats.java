import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] result;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n or trails is less or equlas to 0");
        result = new double[trials];
        while (trials-- > 0) {
            double res = getResult(n);
            result[trials] = res;
        }

        mean = StdStats.mean(result);
        stddev = StdStats.stddev(result);
        confidenceLo = mean - 1.96 * stddev / Math.sqrt(result.length);
        confidenceHi = mean + 1.96 * stddev / Math.sqrt(result.length);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    private double getResult(int n) {
        Percolation percolation = new Percolation(n);
        int open = 0;
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            if (!percolation.isOpen(row, col)) {
                percolation.open(row, col);
                open++;
            }
        }
        double res = open / Math.pow(n, 2);
        return res;
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = new Integer(args[0]).intValue();
        int trials = new Integer(args[1]).intValue();
        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.printf("mean                    = %f\n", percolationStats.mean());
        StdOut.printf("stddev                  = %f\n", percolationStats.stddev());
        StdOut.printf("95%% confidence interval = %f, %f\n", percolationStats.confidenceLo(),
                percolationStats.confidenceHi());
    }
}
