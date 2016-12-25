import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solver {

    private Node solution;
    private boolean solvable = false;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        MinPQ<Node> originalPQ = new MinPQ<>();
        MinPQ<Node> parallelPQ = new MinPQ<>();
        originalPQ.insert(new Node(initial));
        parallelPQ.insert(new Node(initial.twin()));

        while (!originalPQ.isEmpty() && !parallelPQ.isEmpty()) {
            Node result = addNeighbors(originalPQ);
            if (result != null) {
                solution = result;
                solvable = true;
                return;
            }
            result = addNeighbors(parallelPQ);
            if (result != null) {
                solvable = false;
                return;
            }
        }
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (solvable) return solution.movesSoFar;
        else return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!solvable) return null;
        Deque<Board> stack = new ArrayDeque<>();
        Node cur = solution;
        while (cur != null) {
            stack.push(cur.board);
            cur = cur.prev;
        }
        return stack;
    }

    private Node addNeighbors(MinPQ<Node> pq) {
        Node cur = pq.delMin();
        if (cur.board.isGoal()) return cur;
        for (Board neighbor : cur.board.neighbors()) {
            if (cur.prev == null || !neighbor.equals(cur.prev.board)) {
                pq.insert(new Node(neighbor, cur.movesSoFar + 1, cur));
            }
        }
        return null;
    }

    private class Node implements Comparable<Node> {

        private int movesSoFar;
        private Board board;
        private Node prev;

        public Node(Board board) {
            this(board, 0, null);
        }

        public Node(Board board, int movesSoFar, Node prev) {
            Node.this.board = board;
            Node.this.movesSoFar = movesSoFar;
            Node.this.prev = prev;
        }

        public int priority() {
            return board.manhattan() + movesSoFar;
        }

        @Override
        public int compareTo(Node o) {
            return priority() - o.priority();
        }
    }
}