import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * A fast sort-based algorithm to find all line segments, given an array of points.
 *
 * <p>Time complexity : O(N^2lgN)
 *
 * @author kshang
 */
public class FastCollinearPoints {

    private static final int MIN_POINT = 4;
    private final LineSegment[] lineSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        // do not mutate the input
        points = points.clone();
        // sort by y, when y are equal then by x, in ascending order
        // Time : O(NlgN)
        Arrays.sort(points);
        checkDuplicate(points);

        List<LineSegment> lines = new ArrayList<>();
        // Time : O(N^2lgN)
        for (Point p : points) {
            Point[] copy = points.clone();
            // sort by slope, use p as the origin
            Arrays.sort(copy, p.slopeOrder());
            findLines(copy, p, lines);
        }
        lineSegments = lines.toArray(new LineSegment[lines.size()]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        // defensive return clone of internal value
        return lineSegments.clone();
    }

    private void findLines(Point[] sortedBySlope, Point origin, List<LineSegment> res) {
        double s = origin.slopeTo(sortedBySlope[0]);
        int count = 2;
        int left = 1;
        for (int i = 1; i < sortedBySlope.length; i++) {
            double newSlope = origin.slopeTo(sortedBySlope[i]);
            if (newSlope == s) count++;
            if (newSlope != s || i == sortedBySlope.length - 1) {
                if (count >= MIN_POINT && startsWithOrigin(origin, left, sortedBySlope)) {
                    // We only add once a max-line-segment, when origin is the left-most point
                    Point[] points = new Point[2];
                    points[0] = origin;
                    points[1] = sortedBySlope[left + count - 2];
                    addLines(res, points);
                }
                s = newSlope;
                count = 2;
                left = i;
            }
        }
    }

    private boolean startsWithOrigin(Point origin, int left, Point[] sortedBySlope) {
        return origin.compareTo(sortedBySlope[left]) < 0;
    }

    private void addLines(List<LineSegment> lines, Point[] points) {
        for (int i = 1; i < points.length; i++) {
            lines.add(new LineSegment(points[i-1], points[i]));
        }
    }

    private void checkDuplicate(Point[] points) {
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i-1]) == 0) throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}