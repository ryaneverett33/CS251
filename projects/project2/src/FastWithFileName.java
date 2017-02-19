/**
 * Created by Ryan on 2/10/2017.
 */

import java.io.*;
import java.util.*;

//Specifically for use with AlgoStats.java
public class FastWithFileName {
    //Think of p as the origin. For each other point q,
    //determine the angle it makes with p.
    //Sort the points according to the angle they makes with p.
    //Check if any 3 (or more) adjacent points in the sorted order have equal angles with p.
    //If so, these points, together with p , are collinear.
    public static void main(String[] args) throws Exception {
        //Input N /n x y
        //BufferedWriter writer = new BufferedWriter(new FileWriter("visualPoints.txt"));
        Point[] points;
        int arrCount = 0;           //Same as N
        int pointIndex = 0;
        Scanner s = new Scanner(new File(args[0]));
        arrCount = s.nextInt();
        points = new Point[arrCount];
        while (s.hasNextLine()) {
            String line = s.nextLine();
            //System.out.println("Read: " + line + ", length: " + line.length());
            if (!line.equals("")) {
                String firstX = line.substring(0,6);
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
                //float headSlope = calcSlope(angleSorted[i], angleSorted[firstIndex]);
                double headSlope = angleSorted[i].calcSlope(angleSorted[firstIndex]);
                //check angles against firstSlope
                //while (lastIndex < arrCount && (calcSlope(angleSorted[i],angleSorted[lastIndex]) == headSlope)) {
                while (lastIndex < arrCount && (angleSorted[i].calcSlope(angleSorted[lastIndex]) == headSlope)) {
                    //point is colinear
                    //check for more colinear points.
                    // If a point isn't colinear, we go to the conditional and check if it's a valid segment
                    lastIndex = lastIndex + 1;
                }
                //check if valid segment
                if (lastIndex - firstIndex >= 3) {
                    //we can have THREE or more colinear segments
                    //is valid, add points
                    double pointerSlope = Double.NEGATIVE_INFINITY;
                    while (firstPointer < i) {
                        //pointerSlope = calcSlope(angleSorted[i], angleSorted[firstPointer]);
                        pointerSlope = angleSorted[i].calcSlope(angleSorted[firstPointer]);
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
                        //System.out.println(buildResult);
                        //writer.write(buildResult);
                        //writer.newLine();
                    }
                }
                //not valid segment
                else {}
                //set location pointers
                firstIndex = lastIndex;
                lastIndex = lastIndex + 1;
            }
            //writer.flush();
        }
    }
    public static double calcSlope(Point a, Point b) {
        float debugFloat = (float)(b.y - a.y) / (float)(b.x - a.x);
        return debugFloat;
    }
}
