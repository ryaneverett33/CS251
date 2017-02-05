/**
 * Created by Ryan on 2/4/2017.
 */
import java.io.*;
public class PercolationVisualizer {
    public static void main(String[] args) throws IOException {
        int n = StdIn.readInt();
        BufferedWriter writer = new BufferedWriter(new FileWriter("visualMatrix.txt"));
        //The first line will be length of the side of grid i.e N followed by an empty line.
        System.out.println(n);
        System.out.println();
        writer.write(Integer.toString(n));
        writer.newLine();
        writer.newLine();
        Percolation perc = new Percolation(n);
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            if (line.isEmpty()) {
                //empty, skip
                continue;
            }
            String[] objs = line.split(" ");
            int x, y;
            try {
                x = Integer.parseInt(objs[0]);
                y = Integer.parseInt(objs[1]);
            }
            catch (Exception e) {
                //System.out.println("Failed to parse line: " + line);
                continue;
            }
            perc.open(x,y);
            //write out
            for (int i = 0; i < perc.length; i++) {
                for (int j = 0; j < perc.length; j++) {
                    if (j == (perc.length - 1)){
                        System.out.print(perc.grid[i][j] + 1);
                        writer.write(Integer.toString(perc.grid[i][j] + 1));
                    }
                    else {
                        System.out.print(perc.grid[i][j] + 1 + " ");
                        writer.write(Integer.toString(perc.grid[i][j] + 1) + " ");
                    }
                }
                System.out.println();
                writer.newLine();
                writer.flush();
            }
            System.out.println();
            System.out.println();
            writer.newLine();
            writer.flush();
        }
    }
}
