/**
 * Created by Ryan on 2/10/2017.
 */
public class Brute {
    // examines 4 points at a time and checks if they all lie on the same line segment, printing out any such line
    // segments to standard output and a file called "visualPoints.txt"
    public static void Main(String[] args) {
        //Input N /n x y
        int n;
        if (!StdIn.isEmpty()) {
            n = StdIn.readInt();
        }
        while (StdIn.hasNextLine()) {
            String[] split = (String[])StdIn.readLine().split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
        }
    }
}
