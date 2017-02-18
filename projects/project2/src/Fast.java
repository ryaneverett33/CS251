/**
 * Created by Ryan on 2/10/2017.
 */
import java.io.*;
public class Fast {
    //Think of p as the origin. For each other point q,
    //determine the angle it makes with p.
    //Sort the points according to the angle they makes with p.
    //Check if any 3 (or more) adjacent points in the sorted order have equal angles with p.
    //If so, these points, together with p , are collinear.
    public static void Main(String[] args) throws Exception {
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
    }
}
