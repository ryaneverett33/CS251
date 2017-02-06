import java.util.*;

/**
 * Created by everettr on 1/31/17.
 */
public class PercolationStats {
    //Create a grid of size NxN where all cells are blocked initially
    //Randomly select a blocked cell and open it
    //Repeat the previous step until the system percolates
    //Count the number of open cells and use that to obtain the p* estimate.

    public static void main(String[] args) throws Exception {
        //recieves three command line arguments, N, T, and type
        //N : grid size
        //T : repetitions
        //type : either slow or fast, QuickUnion versus WeightedQuickUnion

        //Parse arguments
        int n, t;
        String type;
        if (args.length == 3) {
            type = args[2];
            n = Integer.parseInt(args[0]);
            t = Integer.parseInt(args[1]);
        } else {
            System.out.println("Invalid arguments");
            return;
        }
        n = n;

        int[] threshhold = new int[t];          //number of cells used to percolate
        double[] time = new double[t];
        System.out.println();

        //fill the array of locations
        if (type.equals("slow")) {
            //System.out.println("looping");
            for (int i = 0; i < t; i++) {
                Percolation perc = new Percolation(n);
                ArrayList locations = new ArrayList(n * n);
                int locationCount = n * n;              //decrement after removing from locations
                Random rand = new Random();
                for (int x = 0; x < (n * n); x++) {
                    locations.add(x);
                }

                int cellCount = 0;
                Stopwatch stop = new Stopwatch();
                while (!perc.percolates()) {
                    int randIndex = rand.nextInt(locationCount);     //Inclusive nature covers out of range number
                    int loc = (int) locations.get(randIndex);
                    locations.remove(randIndex);
                    locationCount = locationCount - 1;
                    int[] coords = getCoords(loc, n);
                    perc.open(coords[0], coords[1]);
                    cellCount = cellCount + 1;
                }
                time[i] = stop.elapsedTime();
                threshhold[i] = cellCount;
                //System.out.println(String.format("Iteration: %d, time: %f", i, stop.elapsedTime()));
            }
            threshhold = threshhold;
        } else {
            for (int i = 0; i < t; i++) {
                PercolationQuick perc = new PercolationQuick(n);
                ArrayList locations = new ArrayList(n * n);
                int locationCount = n * n;              //decrement after removing from locations
                Random rand = new Random();
                for (int x = 0; x < (n * n); x++) {
                    locations.add(x);
                }

                int cellCount = 0;
                Stopwatch stop = new Stopwatch();
                while (!perc.percolates()) {
                    int randIndex = rand.nextInt(locationCount);     //Inclusive nature covers out of range number
                    int loc = (int) locations.get(randIndex);
                    locations.remove(randIndex);
                    locationCount = locationCount - 1;
                    int[] coords = getCoords(loc, n);
                    perc.open(coords[0], coords[1]);
                    cellCount = cellCount + 1;
                }
                time[i] = stop.elapsedTime();
                threshhold[i] = cellCount;
                //System.out.println(String.format("Iteration: %d, time: %f", i, stop.elapsedTime()));
            }
            threshhold = threshhold;
        }
        //do actual statistics
        //calculate mean
        double timeSum = 0, cellSum = 0;
        for (int i = 0; i < t; i++) {
            timeSum = timeSum + time[i];
            cellSum = cellSum + threshhold[i];
        }
        double timeMean = (timeSum / (double)t), thresholdMean = ((double)cellSum / (double)t);
        //calculate stdDev
        double stdThreshSum = 0, stdTimeSum = 0;
        for (int i = 0; i < t; i++) {
            stdThreshSum = stdThreshSum + Math.pow(threshhold[i] - thresholdMean,2);
            stdTimeSum = stdTimeSum + Math.pow(time[i] - timeMean, 2);
        }
        double stdThreshold = 0, stdTime = 0;
        stdThreshold = Math.sqrt(stdThreshSum / (t - 1));
        stdTime = Math.sqrt(stdTimeSum / (t - 1));
        printStats(thresholdMean,stdThreshold,timeSum,timeMean,stdTime);
    }

    //int arrPosition = (length * row) + col;
    public static int[] getCoords(int arrPosition, int n) {
        int[] coords = new int[2];
        //row
        coords[0] = arrPosition / n;
        //column
        coords[1] = arrPosition % n;
        return coords;
    }

    public static void printStats(double meanThresh, double stdThresh, double time, double meanTime, double stdTime) {
        //**OUTPUT BELOW**
        //mean threshold=the_threshold_value
        //std dev=the_threshold_stddev
        //time=total_time_in_seconds
        //mean time=mean_time_in_seconds
        //stddev time=stddev_time_in_second
        System.out.println("**OUTPUT BELOW**");
        System.out.println(String.format("mean threshold=%f", meanThresh));
        System.out.println(String.format("std dev=%f", stdThresh));
        System.out.println(String.format("time=%f", time));
        System.out.println(String.format("mean time=%f", meanTime));
        System.out.println(String.format("stddev time=%f", stdTime));
    }
}
