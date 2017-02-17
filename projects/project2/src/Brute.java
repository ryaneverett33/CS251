/**
 * Created by Ryan on 2/10/2017.
 */
import java.util.*;
public class Brute {
    // examines 4 points at a time and checks if they all lie on the same line segment, printing out any such line
    // segments to standard output and a file called "visualPoints.txt"
    public static void main(String[] args) {
        //Input N /n x y
        Point[] points;
        int arrCount = 0;           //Same as N
        int pointIndex = 0;
        if (!StdIn.isEmpty()) {
            arrCount = StdIn.readInt();
        }
        points = new Point[arrCount];
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            System.out.println("Read: " + line + ", length: " + line.length());
            if (!line.equals("")) {
                String firstX = line.substring(0,5);
                firstX = firstX.trim();
                String firstY = line.substring(6);
                firstY = firstY.trim();
                //System.out.println("FirstX: " + firstX + ", firstY: " + firstY);
                int x = Integer.parseInt(firstX);
                int y = Integer.parseInt(firstY);
                points[pointIndex] = (new Point(x, y));
                pointIndex = pointIndex + 1;
            }
        }
        if (arrCount >= 4) {
            listSort(points);
            //for (int i = 0; i < (arrCount - 3); i++) {
            for (int i = 0; i < (arrCount - 3); i++) {
                if (Point.areCollinear(points[i], points[i+1], points[i+2], points[i+3])) {
                    System.out.println("Colinear!");
                }
                System.out.println("Point[" + i + "] = " + points[i].toString());
            }
        }
    }
    public static void listSort (Point[] list) {
        int n = list.length;
        for (int j = 1; j < n; j++) {
            Point key = list[j];
            int i = j-1;
            //list [i] > key
            while ( (i > -1) && ( isGreater(list[i],key) ) ) {
                list [i+1] = list [i];
                i--;
            }
            list[i+1] = key;
        }
    }
    //true - a is greater
    //false - b is greater
    public static boolean isGreater(Point a, Point b) {
        if (a.x > b.x) {
            return true;
        }
        return false;
    }
}
