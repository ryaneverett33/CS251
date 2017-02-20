import javafx.scene.paint.Stop;

import java.io.*;
import java.util.*;

/**
 * Created by Ryan on 2/19/2017.
 */
public class AlgoStats {
    Random rand = new Random();
    public static void main(String[] args) throws Exception {
        //use provided input.txt
        //10, 20, 50, 100, 200, 400, 1000, 2000, 4000, 10000,
        int[] nValues = {10,20,40,50, 80 ,100,150, 200,250,300,350,400,1000};
        String[] inputFiles = {
            "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input10.txt",
                "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input20.txt",
                "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input40.txt",
                "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input50.txt",
                "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input80.txt",
                "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input100.txt",
                "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input150.txt",
                "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input200.txt",
                "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input250.txt",
                "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input300.txt",
                "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input350.txt",
                "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input400.txt",
                "C:\\Users\\Ryan\\Repositories\\cs251\\projects\\project2\\src\\collinear-tests\\input1000.txt",
        };
        double[] bruteTimes = new double[inputFiles.length];
        double[] fastTimes = new double[inputFiles.length];
        //for (int i = 0; i < inputFiles.length; i++) {
        /*for (int i = 0; i < inputFiles.length; i++) {
            String[] commandArgs = { inputFiles[i]};
                Stopwatch stop = new Stopwatch();
                //BruteWithFileName.main(commandArgs);
                //bruteTimes[i] = stop.elapsedTime();
                //System.out.println("Brute Time(" + nValues[i] +"): "  + bruteTimes[i]);
                stop = new Stopwatch();
                FastWithFileName.main(commandArgs);
                fastTimes[i] = stop.elapsedTime();
                System.out.println("Fast Time(" + nValues[i] +"): " + fastTimes[i]);
        }*/
        String[] commandArgs = { inputFiles[0]};
        for (int i = 0; i < 32; i++) {
            Stopwatch stop = new Stopwatch();
            BruteWithFileName.main(commandArgs);
            System.out.println(i + ": " + stop.elapsedTime());
        }

        //Print Results
        /*System.out.println();
        System.out.println("RESULTS");
        System.out.println("----Brute-----");
        for (int i = 0; i < inputFiles.length; i++) {
            System.out.println(nValues[i] + ": " + bruteTimes[i]);
        }
        System.out.println("----Fast-----");
        for (int i = 0; i < inputFiles.length; i++) {
            System.out.println(nValues[i] + ": " + fastTimes[i]);
        }*/
    }
}
