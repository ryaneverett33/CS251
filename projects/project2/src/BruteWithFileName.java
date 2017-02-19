/**
 * Created by Ryan on 2/10/2017.
 */

import java.io.*;
import java.util.*;

//Specifically for use with AlgoStats.java
public class BruteWithFileName {
    // examines 4 points at a time and checks if they all lie on the same line segment, printing out any such line
    // segments to standard output and a file called "visualPoints.txt"
    public static void main(String[] args) throws Exception{
        //Input N /n x y
        //BufferedWriter writer = new BufferedWriter(new FileWriter("visualPoints.txt"));

        //Debug read in
        /*String inputFile = "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\tests\\input50.txt";
        System.setIn(new FileInputStream(inputFile));*/

        Scanner s = new Scanner(new File(args[0]));
        Point[] points;
        int arrCount = 0;           //Same as N
        int pointIndex = 0;
        arrCount = s.nextInt();
        points = new Point[arrCount];


        while (s.hasNextLine()) {
            String line = s.nextLine();
            //System.out.println("Read: " + line + ", length: " + line.length());
            if (!line.equals("") || !line.isEmpty()) {
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
        if (arrCount >= 4) {
            //listSort(points);
            Arrays.sort(points);
            for (int i = 0; i < arrCount - 3 ; i++) {
                for (int j = i+1; j < arrCount - 2; j++) {
                    for (int k = j+1; k < arrCount - 1; k++) {
                        for (int l = k+1; l < arrCount; l++) {
                            boolean collinear = Point.areCollinear(points[i], points[j], points[k], points[l]);
                            if (collinear) {
                                String pointStr = formatCollinear(points[i], points[j], points[k], points[l]);
                                //System.out.println(pointStr);
                                //writer.write(pointStr);
                                //writer.newLine();
                            }
                            else {
                                //check individual cases
                                //(1000, 2000) -> (1000, 9000) -> (1000, 13000) -> (1000, 23000)
                                //4:(1000, 2000) -> (1000, 9000) -> (1000, 13000) -> (1000, 26000)
                                //4:(1000, 2000) -> (1000, 9000) -> (1000, 23000) -> (1000, 26000)
                                //4:(1000, 9000) -> (1000, 13000) -> (1000, 23000) -> (1000, 26000)
                                //4:(18000, 13000) -> (18000, 23000) -> (18000, 26000) -> (18000, 27000)
                                //4:(18000, 13000) -> (18000, 23000) -> (18000, 26000) -> (18000, 30000)
                                //4:(18000, 13000) -> (18000, 23000) -> (18000, 27000) -> (18000, 30000)
                                //4:(18000, 13000) -> (18000, 26000) -> (18000, 27000) -> (18000, 30000)
                                //4:(18000, 23000) -> (18000, 26000) -> (18000, 27000) -> (18000, 30000)
                                /*if (points[i].x == 1000 && (points[i].y == 2000  || points[i].y == 9000)) {
                                    if (points[j].x == 1000 && points[k].x == 1000 && points[l].x == 1000) {
                                        printSlope(points[i], points[j], points[k], points[l]);
                                    }
                                }
                                else if (points[i].x == 18000 && points[j].x == 18000 && points[k].x == 18000 &&
                                        points[l].x == 18000) {
                                    printSlope(points[i], points[j], points[k], points[l]);
                                }*/
                            }
                        }
                    }
                }
            }
            //writer.flush();
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
    public static void printSlope(Point p, Point q, Point r, Point s) {
        float slope12 = (float)(q.y - p.y) / (float)(q.x - p.x);
        float slope23 = (float)(r.y - q.y) / (float)(r.x - q.x);
        System.out.println(String.format("Slope12: %f, Slope23: %f", slope12, slope23));
        if (slope12 != slope23) {
            System.out.println("FALSE");
        }
        float slope34 = (float)(s.y - r.y) / (float)(s.x - r.x);
        float slope13 = (float)(r.y - p.y) / (float)(r.x - p.x);
        System.out.println(String.format("Slope34: %f, Slope13: %f", slope34, slope13));
        if (slope34 != slope13) {
            System.out.println("FALSE");
        }
        //since slope34 == slope13 && slope12 == slope23, then test if slope34 == slope34 then slope12,23,34,13 are =
        if (slope23 != slope34) {
            System.out.println("FALSE");
        }
        float slope14 = (float)(s.y - p.y) / (float)(s.x - p.x);
        float slope24 = (float)(s.y - q.y) / (float)(s.x - q.x);
        System.out.println(String.format("Slope14: %f, Slope24: %f", slope14, slope24));
        if (slope14 != slope24) {
            System.out.println("FALSE");
        }
        if (slope13 != slope14) {
            System.out.println("FALSE");
        }
        System.out.println("TRUE");
    }
}
