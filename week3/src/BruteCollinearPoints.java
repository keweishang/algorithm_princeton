import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        // do not mutate the input
        points = points.clone();
        // Sort points by ascending order (order y-axis, if y-axis are equal, by x-axis)
        Arrays.sort(points);
        checkDuplicate(points);


        List<LineSegment> list = new ArrayList<>();
        for (int p = 0; p <= points.length - 4; p++)
            for (int q = p + 1; q <= points.length - 3; q++)
                for (int r = q + 1; r <= points.length - 2; r++)
                    for (int s = r + 1; s <= points.length - 1; s++) {
                        double slope1 = points[p].slopeTo(points[q]);
                        double slope2 = points[q].slopeTo(points[r]);
                        double slope3 = points[r].slopeTo(points[s]);
                        if (slope1 == Double.NEGATIVE_INFINITY
                                || slope2 == Double.NEGATIVE_INFINITY
                                || slope3 == Double.NEGATIVE_INFINITY)
                            throw new IllegalArgumentException();
                        if (slope1 == slope2 && slope2 == slope3) {
                            list.add(new LineSegment(points[p], points[s]));
                        }
                    }
        lineSegments = list.toArray(new LineSegment[list.size()]);
    }

    private void checkDuplicate(Point[] points) {
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i-1]) == 0) throw new IllegalArgumentException();
        }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}