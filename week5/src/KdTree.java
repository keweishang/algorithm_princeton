import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A mutable data type that uses a 2d-tree to implement the 2D range search and nearest point search.
 * <p>
 * A 2d-tree is a generalization of a BST to two-dimensional keys.
 * The idea is to build a BST with points in the nodes, using the x- and y-coordinates of the points
 * as keys in strictly alternating sequence.
 *
 * @author kshang
 */
public class KdTree {

    private Node root;
    private int size = 0;

    // construct an empty set of points
    public KdTree() {
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException();
        root = insert(root, p, 0D, 1D, 0D, 1D, true);
    }

    private Node insert(Node cur, Point2D p, double xmin, double xmax, double ymin, double ymax, boolean horizontal) {
        if (cur == null) {
            size++;
            return new Node(p, new RectHV(xmin, ymin, xmax, ymax));
        } else {
            if (!p.equals(cur.point)) {
                if (horizontal) {
                    if (p.x() < cur.point.x()) cur.lb = insert(cur.lb, p, xmin, cur.point.x(), ymin, ymax, !horizontal);
                    else cur.rt = insert(cur.rt, p, cur.point.x(), xmax, ymin, ymax, !horizontal);
                } else {
                    if (p.y() < cur.point.y()) cur.lb = insert(cur.lb, p, xmin, xmax, ymin, cur.point.y(), !horizontal);
                    else cur.rt = insert(cur.rt, p, xmin, xmax, cur.point.y(), ymax, !horizontal);
                }
            }
            return cur;
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException();
        Node cur = root;
        boolean horizontal = true;
        while (cur != null) {
            if (cur.point.equals(p)) return true;
            if (horizontal) {
                if (p.x() < cur.point.x()) cur = cur.lb;
                else cur = cur.rt;
            } else {
                if (p.y() < cur.point.y()) cur = cur.lb;
                else cur = cur.rt;
            }
            horizontal = !horizontal;
        }
        return false;
    }

    // draw all points to standard draw
    public void draw() {
        draw(root, 0D, 1D, 0D, 1D, false);
    }

    private void draw(Node cur, double minX, double maxX, double minY, double maxY, boolean horizontal) {
        if (cur == null) return;
        if (horizontal) {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(minX, cur.point.y(), maxX, cur.point.y());

            // x-axis min and max do not change
            draw(cur.lb, minX, maxX, minY, cur.point.y(), !horizontal);
            draw(cur.rt, minX, maxX, cur.point.y(), maxY, !horizontal);
        } else {
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(cur.point.x(), minY, cur.point.x(), maxY);

            // y-axis min and max do not change
            draw(cur.lb, minX, cur.point.x(), minY, maxY, !horizontal);
            draw(cur.rt, cur.point.x(), maxX, minY, maxY, !horizontal);
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> res = new ArrayList<>();
        range(root, rect, true, res);
        return res;
    }

    private void range(Node cur, RectHV rect, boolean horizontal, List<Point2D> res) {
        if (cur == null) return;
        // intersect with current node's point
        if (rect.contains(cur.point)) res.add(cur.point);
        if (horizontal) {
            if (rect.xmin() < cur.point.x()) range(cur.lb, rect, !horizontal, res);
            if (rect.xmax() >= cur.point.x()) range(cur.rt, rect, !horizontal, res);
        } else {
            if (rect.ymin() < cur.point.y()) range(cur.lb, rect, !horizontal, res);
            if (rect.ymax() >= cur.point.y()) range(cur.rt, rect, !horizontal, res);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException();
        Node nearestNode = nearest(root, p, root, true);
        if (nearestNode == null) return null;
        return nearestNode.point;
    }

    private Node nearest(Node cur, Point2D p, Node nearestNode, boolean horizontal) {
        if (cur == null) return nearestNode;
        // No need to search the other subtree if the nearest distance is smaller than the distance between p and the other half
        if (cur.rect.distanceTo(p) > nearestNode.point.distanceTo(p)) return nearestNode;
        if (cur.point.distanceTo(p) < nearestNode.point.distanceTo(p)) nearestNode = cur;
        Node subTree1;
        Node subTree2;
        if (horizontal) {
            if (p.x() < cur.point.x()) {
                subTree1 = cur.lb;
                subTree2 = cur.rt;
            } else {
                subTree1 = cur.rt;
                subTree2 = cur.lb;
            }
        } else {
            if (p.y() < cur.point.y()) {
                subTree1 = cur.lb;
                subTree2 = cur.rt;
            } else {
                subTree1 = cur.rt;
                subTree2 = cur.lb;
            }
        }

        nearestNode = nearest(subTree1, p, nearestNode, !horizontal);
        nearestNode = nearest(subTree2, p, nearestNode, !horizontal);
        return nearestNode;
    }

    private static class Node {
        private Point2D point;
        private RectHV rect;    // the rectangle corresponding to the node
        private Node lb;        // left/bottom subtree
        private Node rt;        // right/top subtree

        public Node(Point2D point, RectHV rect) {
            this.point = point;
            this.rect = rect;
        }
    }
}