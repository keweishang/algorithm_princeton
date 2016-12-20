import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;


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


        Set<Double> slopes = new HashSet<>();
        List<LineSegment> lines = new ArrayList<>();
        // Time : O(N^2lgN)
        for (Point p : points) {
            Point[] copy = points.clone();
            // sort by slope, use p as the origin
            Arrays.sort(copy, p.slopeOrder());
            findLines(copy, p, lines, slopes);
        }
        lineSegments = lines.toArray(new LineSegment[lines.size()]);
    }

    private void findLines(Point[] copy, Point p, List<LineSegment> lines, Set<Double> slopes) {
        double s = p.slopeTo(copy[0]);
        int count = 2;
        int left = 1;
        for (int i = 1; i < copy.length; i++) {
            double newSlope = p.slopeTo(copy[i]);
            if (newSlope == s) count++;
            if (newSlope != s || i == copy.length - 1) {
                if (count >= MIN_POINT) {
                    if (!slopes.contains(s)) {
                        // haven't added the line
                        slopes.add(s);
                        Point[] points = new Point[count];
                        System.arraycopy(copy, left, points, 1, count - 1);
                        points[0] = p;
                        addLines(lines, points);
                    }
                }
                s = newSlope;
                count = 2;
                left = i;
            }
        }
    }

    private void addLines(List<LineSegment> lines, Point[] points) {
        for (int i = 1; i < points.length; i++) {
            LineSegment line = new LineSegment(points[i-1], points[i]);
            lines.add(line);
        }
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