/**
 * Created by Ryan on 2/10/2017.
 */
import java.io.*;
import java.util.*;
public class Brute {
    // examines 4 points at a time and checks if they all lie on the same line segment, printing out any such line
    // segments to standard output and a file called "visualPoints.txt"
    public static void main(String[] args) throws Exception{
        //Input N /n x y
        BufferedWriter writer = new BufferedWriter(new FileWriter("visualPoints.txt"));
        Point[] points;
        int arrCount = 0;           //Same as N
        int pointIndex = 0;
        if (!StdIn.isEmpty()) {
            arrCount = StdIn.readInt();
        }
        points = new Point[arrCount];
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            //System.out.println("Read: " + line + ", length: " + line.length());
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
            for (int i = 0; i < (arrCount - 3); i++) {
                for (int j = i+1; j < (arrCount - 2); j++) {
                    for (int k = j+1; k < (arrCount - 1); k++) {
                        for (int l = k+1; l < arrCount; l++) {
                            if (Point.areCollinear(points[i], points[j], points[k], points[l])) {
                                String pointStr = formatCollinear(points[i], points[j], points[k], points[l]);
                                System.out.println(pointStr);
                                writer.write(pointStr);
                                writer.newLine();
                            }
                        }
                    }
                }
            }
            writer.flush();
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
    public static String formatCollinear(Point a, Point b, Point c, Point d) {
        //4:(0, 10000) -> (3000, 7000) -> (7000, 3000) -> (10000, 0)
        return String.format("4:(%d, %d) -> (%d, %d) -> (%d, %d) -> (%d, %d)",
                a.x, a.y, b.x, b.y, c.x, c.y, d.x, d.y);
    }
}
