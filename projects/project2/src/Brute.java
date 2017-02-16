/**
 * Created by Ryan on 2/10/2017.
 */
import java.util.*;
public class Brute {
    // examines 4 points at a time and checks if they all lie on the same line segment, printing out any such line
    // segments to standard output and a file called "visualPoints.txt"
    public static void main(String[] args) {
        //Input N /n x y
        int n;
        ArrayList<Point> points = new ArrayList<>(5);
        int arrCount = 0;
        if (!StdIn.isEmpty()) {
            n = StdIn.readInt();
        }
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            System.out.println("Read: " + line + ", length: " + line.length());
            if (!line.equals("")) {
                String firstX = line.substring(0,5);
                firstX = firstX.trim();
                String firstY = line.substring(6);
                firstY = firstY.trim();
                System.out.println("FirstX: " + firstX + ", firstY: " + firstY);
                int x = Integer.parseInt(firstX);
                int y = Integer.parseInt(firstY);
                points.add(new Point(x, y));
                arrCount = arrCount + 1;
            }
        }
        if (arrCount >= 4) {
            for (int i = 0; i < (arrCount - 3); i++) {
                if (Point.areCollinear(points.get(i), points.get(i+1), points.get(i+2), points.get(i+3))) {
                    System.out.println("Colinear!");
                }
            }
        }
    }
}
