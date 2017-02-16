/*************************************************************************
 * Compilation:  javac Point.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point>{
    // compare points by slope
    public final Comparator<Point> BY_SLOPE_ORDER = null;    // YOUR DEFINITION HERE

    public final int x;                              // x coordinate
    public final int y;                              // y coordinate

    // constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // are the 3 points p, q, and r collinear?
    public static boolean areCollinear(Point p, Point q, Point r) {
        /* YOUR CODE HERE */
        //slope = (y2-y1)/(x2-1)
        float slope1 = (q.y - p.y) / (q.x - p.x);
        float slope2 = (r.y - q.y) / (r.x - q.x);
        if (slope1 == slope2) {
            return true;
        }
        else {
            return false;
        }
    }

    // are the 4 points p, q, r, and s collinear?
    public static boolean areCollinear(Point p, Point q, Point r, Point s) {
        /* YOUR CODE HERE */
        float slope1 = (q.y - p.y) / (q.x - p.x);
        float slope2 = (r.y - q.y) / (r.x - q.x);
        if (slope1 != slope2) {
            return false;
        }
        float slope3 = (s.y - r.y) / (s.x - r.x);
        if (slope3 != slope2) {
            return false;
        }
        else {
            return true;
        }
    }

    // is this point lexicographically smaller than that one?
    public int compareTo(Point that) {
        // -1 this is less than that
        // 0 equal
        // 1 this is greater than that
        /* YOUR CODE HERE */
        if (this.x < that.x) {
            return -1;
        }
        if (this.x > that.x) {
            return -1;
        }
        if (this.y < that.y) {
            return 1;
        }
        if (this.y > that.y) {
            return 1;
        }
        return 0;
    }

}
