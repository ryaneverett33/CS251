/*************************************************************************
 * Compilation:  javac Point.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point>{
    // compare points by slope
    public final Comparator<Point> BY_SLOPE_ORDER = new Comparator<Point>() {
        @Override
        //Sort the points according to the angle they make with the origin (p)
        public int compare(Point a, Point b) {
            //float slopeToA = (float)(a.y - y) / (float)(a.x - x);
            //float slopeToB = (float)(b.y - y) / (float)(b.x - x);
            double slopeToA = calcSlope(a);
            double slopeToB = calcSlope(b);
            if (slopeToA > slopeToB) {
                return 1;
            }
            if (slopeToA < slopeToB) {
                return -1;
            }
            return 0;
        }
    };

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
        /*float slope1 = (q.y - p.y) / (q.x - p.x);
        float slope2 = (r.y - q.y) / (r.x - q.x);
        if (slope1 == slope2) {
            return true;
        }
        else {
            return false;
        }*/
        float slope12 = (float)(q.y - p.y) / (float)(q.x - p.x);
        float slope23 = (float)(r.y - q.y) / (float)(r.x - q.x);
        if (slope12 != slope23) {
            return false;
        }
        float slope13 = (float)(r.y - p.y) / (float)(r.x - p.x);
        if (slope13 != slope23) {
            return false;
        }
        return true;
    }

    // are the 4 points p, q, r, and s collinear?
    public static boolean areCollinear(Point p, Point q, Point r, Point s) {
        //float slope12 = (float)(q.y - p.y) / (float)(q.x - p.x);
        //float slope23 = (float)(r.y - q.y) / (float)(r.x - q.x);
        double slope12 = p.calcSlope(q);
        double slope23 = q.calcSlope(r);
        if (slope12 != slope23) {
            return false;
        }
        //float slope34 = (float)(s.y - r.y) / (float)(s.x - r.x);
        //float slope13 = (float)(r.y - p.y) / (float)(r.x - p.x);
        double slope34 = r.calcSlope(s);
        double slope13 = p.calcSlope(r);
        if (slope34 != slope13) {
            return false;
        }
        //since slope34 == slope13 && slope12 == slope23, then test if slope34 == slope34 then slope12,23,34,13 are =
        if (slope23 != slope34) {
            return false;
        }
        //float slope14 = (float)(s.y - p.y) / (float)(s.x - p.x);
        //float slope24 = (float)(s.y - q.y) / (float)(s.x - q.x);
        double slope14 = p.calcSlope(s);
        double slope24 = q.calcSlope(s);
        if (slope14 != slope24) {
            return false;
        }
        if (slope13 != slope14) {
            return false;
        }
        return true;
    }
    public static void printSlopes(Point p, Point q, Point r, Point s) {
        float slope12 = (float)(q.y - p.y) / (float)(q.x - p.x);
        float slope23 = (float)(r.y - q.y) / (float)(r.x - q.x);
        float slope34 = (float)(s.y - r.y) / (float)(s.x - r.x);
        float slope13 = (float)(r.y - p.y) / (float)(r.x - p.x);
        float slope14 = (float)(s.y - p.y) / (float)(s.x - p.x);
        float slope24 = (float)(s.y - q.y) / (float)(s.x - q.x);
        System.out.println(String.format("12: %f, 23: %f, 34: %f, 13: %f, 14: %f, 24: %f",
                slope12, slope23, slope34, slope13, slope14, slope24));
    }

    // is this point lexicographically smaller than that one?
    public int compareTo(Point that) {
        // -1 this is less than that
        // 0 equal
        // 1 this is greater than that

        //segment. The order meant here is the lexicographical order on the
        //points coordinates that puts points in ascending order first by x coordinate and then followed by y coordinate
        //(if x coordinates are equal)
        if (this.x < that.x) {
            return -1;
        }
        if (this.x > that.x) {
            return 1;
        }

        if (this.y < that.y) {
            return -1;
        }
        if (this.y > that.y) {
            return 1;
        }

        return 0;
    }
    public String toString() {
        //(%d, %d)
        return String.format("(%d, %d)", this.x, this.y);
    }
    public Double calcSlope(Point other) {
        if (x == other.x && y == other.y) {
            return Double.NEGATIVE_INFINITY;
        }
        else if (y == other.y) {
            return 0d;
        }
        else if (x == other.x) {
            return Double.POSITIVE_INFINITY;
        }
        else {
            return (double)(other.y - y) / (double) (other.x - x);
        }
    }
}
