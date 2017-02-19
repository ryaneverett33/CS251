/**
 * Created by Ryan on 2/10/2017.
 */
import java.io.*;
import java.util.*;
public class Fast {
    //Think of p as the origin. For each other point q,
    //determine the angle it makes with p.
    //Sort the points according to the angle they makes with p.
    //Check if any 3 (or more) adjacent points in the sorted order have equal angles with p.
    //If so, these points, together with p , are collinear.
    public static void main(String[] args) throws Exception {
        //Input N /n x y
        BufferedWriter writer = new BufferedWriter(new FileWriter("visualPoints.txt"));
        Point[] points;
        int arrCount = 0;           //Same as N
        /*int pointIndex = 0;
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
        }*/
        //Debug create

        //input6.txt
        /*arrCount = 6;
        points = new Point[arrCount];
        points[0] = new Point(19000, 10000);
        points[1] = new Point(18000, 10000);
        points[2] = new Point(32000, 10000);
        points[3] = new Point(21000, 10000);
        points[4] = new Point(1234, 5678);
        points[5] = new Point(14000, 10000);*/
        //input8.txt
        /*8
        10000      0
            0  10000
         3000   7000
         7000   3000
        20000  21000
         3000   4000
        14000  15000
         6000   7000*/
        arrCount = 8;
        points = new Point[arrCount];
        points[0] = new Point(10000, 0);
        points[1] = new Point(0, 10000);
        points[2] = new Point(3000, 7000);
        points[3] = new Point(7000, 3000);
        points[4] = new Point(20000, 21000);
        points[5] = new Point(3000, 4000);
        points[6] = new Point(14000, 15000);
        points[7] = new Point(6000, 7000);

        Arrays.sort(points);
        //debug print points
        Point[] angleSorted = new Point[arrCount];
        for (int i = 0; i < arrCount - 3; i++) {
            for (int j = i; j < arrCount; j++) {
                angleSorted[j] = points[j];
            }

            //sort by angles
            Arrays.sort(angleSorted, i+1, arrCount, angleSorted[i].BY_SLOPE_ORDER);
            Arrays.sort(angleSorted, 0, i, angleSorted[i].BY_SLOPE_ORDER);

            int firstIndex = i+1;
            int lastIndex = i+2;
            int firstPointer = 0;

            while (lastIndex < arrCount) {
                float headSlope = calcSlope(angleSorted[i], angleSorted[firstIndex]);
                //check angles against firstSlope
                while (lastIndex < arrCount && (calcSlope(angleSorted[i],angleSorted[lastIndex]) == headSlope)) {
                    //point is colinear
                    //check for more colinear points.
                    // If a point isn't colinear, we go to the conditional and check if it's a valid segment
                    lastIndex = lastIndex + 1;
                }
                //check if valid segment
                if (lastIndex - firstIndex >= 3) {
                    //we can have THREE or more colinear segments
                    //is valid, add points
                    float pointerSlope = Float.NEGATIVE_INFINITY;
                    while (firstPointer < i) {
                        pointerSlope = calcSlope(angleSorted[i], angleSorted[firstPointer]);
                        if (pointerSlope < headSlope) firstPointer++;
                        else break;
                    }
                    if (pointerSlope != headSlope) {
                        StringBuilder builder = new StringBuilder();
                        int pointCount = (lastIndex - firstIndex) + 1;
                        builder.append(pointCount + ":");
                        builder.append(angleSorted[i].toString() + " -> ");
                        //System.out.print(angleSorted[i].toString() + " -> ");
                        for (int loc = firstIndex; loc < (lastIndex - 1); loc++) {
                            //print up until the last element
                            //System.out.print(angleSorted[loc].toString() + " -> ");
                            builder.append(angleSorted[loc].toString() + " -> ");
                        }
                        //print last element
                        builder.append(angleSorted[lastIndex - 1].toString());
                        String buildResult = builder.toString();
                        System.out.println(buildResult);
                        writer.write(buildResult);
                        writer.newLine();
                    }
                }
                //not valid segment
                else {}
                //set location pointers
                firstIndex = lastIndex;
                lastIndex = lastIndex + 1;
            }
            writer.flush();
        }
    }
    public static float calcSlope(Point a, Point b) {
        float debugFloat = (float)(b.y - a.y) / (float)(b.x - a.x);
        return debugFloat;
    }
}
