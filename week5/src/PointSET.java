import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A mutable data type that represents a set of points in the unit square.
 * <p>
 * Implementation uses a red-black BST (java.util.TreeSet).
 *
 * @author kshang
 */
public class PointSET {

    private final Set<Point2D> points = new TreeSet<>();

    // construct an empty set of points
    public PointSET() {
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException();
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException();
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : points) {
            p.draw();
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException();
        List<Point2D> res = new ArrayList<>();
        for (Point2D p : points) {
            if (rect.contains(p)) res.add(p);
        }
        return res;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException();
        double nearest = Double.MAX_VALUE;
        Point2D res = null;
        for (Point2D point : points) {
            double distance = point.distanceSquaredTo(p);
            if (distance < nearest) {
                nearest = distance;
                res = point;
            }
        }
        return res;
    }
}