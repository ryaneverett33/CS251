/**
 * Created by Ryan on 2/19/2017.
 */
import java.util.*;
import java.io.*;
public class NGenerator {
    //Assumes N is passed as argument
    public static void main(String[] args) throws Exception{
        Random rand = new Random();
        int n = Integer.parseInt(args[0]);
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("gen_%d.txt", n)));
        writer.write(Integer.toString(n));
        writer.newLine();
        for (int i = 0; i < n; i++) {
            String x = Integer.toString(rand.nextInt(32767));
            String y = Integer.toString(rand.nextInt(32767));
            //18644 12074
            writer.write(String.format("%s %s", pad(x), pad(y)));
            writer.newLine();
            writer.flush();
        }
        writer.flush();
    }
    public static String pad(String s) {
        String copy = s;
        int padLength = 5 - s.length();
        for (int i = 0; i < padLength; i++) {
            copy = copy + " ";
        }
        return copy;
    }
}
