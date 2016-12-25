import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An immutable data type Board
 *
 * @author kshang
 */
public class Board {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private final int[][] blocks;
    private final int totalBlocks;
    private int hamming = -1;
    private int manhattan = -1;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null) throw new NullPointerException("blocks cannot be null");
        this.blocks = copy(blocks);
        this.totalBlocks = blocks.length * blocks[0].length;
    }

    // board dimension n
    public int dimension() {
        return blocks.length;
    }

    // number of blocks out of place
    public int hamming() {
        if (hamming != -1) return hamming;
        int count = 0;
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[0].length; col++) {
                if (to1d(row, col) != blocks[row][col]) count++;
            }
        }

        hamming = blocks[dimension() - 1][dimension() - 1] == 0 ? count : count - 1;
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        if (manhattan != -1) return manhattan;
        int sum = 0;
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[0].length; col++) {
                if (blocks[row][col] == 0) continue;
                int[] position = to2d(blocks[row][col]);
                int targetRow = position[0];
                int targetCol = position[1];
                sum = sum + Math.abs(targetRow - row) + Math.abs(targetCol - col);
            }
        }
        manhattan = sum;
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int position = 1;
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[0].length; col++) {
                if (blocks[row][col] != position++ % totalBlocks) return false;
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[0].length; col++) {
                if (blocks[row][col] != 0) {
                    for (int[] dir : DIRS) {
                        int r = dir[0] + row;
                        int c = dir[1] + col;
                        if (r >= 0 && r < dimension() && c >= 0 && c < dimension() && blocks[r][c] != 0) {
                            int[][] newBlocks = copy(blocks);
                            exch(row, col, r, c, newBlocks);
                            return new Board(newBlocks);
                        }
                    }
                }
            }
        }
        return null;
    }

    // does this board equal y?
    @Override
    public boolean equals(Object y) {
        if (y instanceof Board) {
            Board other = (Board) y;
            for (int row = 0; row < blocks.length; row++) {
                if (!Arrays.equals(blocks[row], other.blocks[row])) return false;
            }
            return true;
        } else return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> list = new ArrayList<>(4);

        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[0].length; col++) {
                if (blocks[row][col] == 0) {
                    // find the blank block
                    for (int[] dir : DIRS) {
                        int r = dir[0] + row;
                        int c = dir[1] + col;
                        if (r >= 0 && r < dimension() && c >= 0 && c < dimension()) {
                            int[][] newBlocks = copy(blocks);
                            exch(row, col, r, c, newBlocks);
                            Board newBoard = new Board(newBlocks);
                            list.add(newBoard);
                        }
                    }
                    return list;
                }
            }
        }

        return list;
    }

    // string representation of this board (in the output format specified below)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dimension()).append('\n');
        for (int row = 0; row < blocks.length; row++) {
            sb.append(' ');
            for (int col = 0; col < blocks[0].length; col++) {
                if (col != 0) sb.append("  ");
                sb.append(blocks[row][col]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    // off-by-1 because the position(0,0) should contain 1, not 0
    private int to1d(int row, int col) {
        return (dimension() * row + col + 1) % (totalBlocks);
    }

    // transform 1D position to 2D position row and col
    private int[] to2d(int position) {
        int row, col;
        row = (position - 1) / dimension();
        col = (position - 1) % dimension();
        return new int[]{row, col};
    }

    private void exch(int row1, int col1, int row2, int col2, int[][] board) {
        int tmp = board[row1][col1];
        board[row1][col1] = board[row2][col2];
        board[row2][col2] = tmp;
    }

    private int[][] copy(int[][] original) {
        int m = original.length;
        int n = original[0].length;
        int[][] copy = new int[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                copy[row][col] = original[row][col];
            }
        }
        return copy;
    }
}