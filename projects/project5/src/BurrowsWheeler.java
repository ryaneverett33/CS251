import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class BurrowsWheeler {
    public static final int R = 256;
    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    //transform
    public static void encode() throws Exception
    {
        String read = BinaryStdIn.readString();
        System.out.println("Read: " + read);
        /*String[] readSplit = read.split(" ");
        ArrayList<Integer> textList = new ArrayList<>(readSplit.length);
        for (int i = 0; i < readSplit.length; i++) {
            textList.add(Integer.parseInt(readSplit[i]));
        }
        Suffix suffix = new Suffix(toPrimitiveArray(textList));*/
        Suffix suffix = new Suffix(read);

        for (int i = 0; i < read.length(); i++) {
            if (suffix.getIndex(i) == 0) {
                //BinaryStdOut.write(i);
                System.out.println(Integer.toString(i));
                break;
            }
        }
        for (int i = 0; i < read.length(); i++) {
        //for (int i = 0; i < readSplit.length; i++) {
            /*if ((suffix.getIndex(i) - 1) < 0) {
                BinaryStdOut.write(read.charAt(read.length() - 1));
                System.out.print(' ');
            }
            else {
                BinaryStdOut.write(read.charAt(suffix.getIndex(i) - 1));
                System.out.print(' ');
            }*/
            /*if (suffix.getIndex(i) - 1 < 0) {

            }
            else {
                System.out.print(read.)
            }*/
            //System.out.print(readSplit[(suffix.getIndex(i) + readSplit.length -1) % readSplit.length]);
            //System.out.print(" ");
            System.out.print(read.charAt((suffix.getIndex(i) + read.length() -1) % read.length()));
            //BinaryStdOut.write(read.charAt((suffix.getIndex(i) + read.length() -1) % read.length()));
        }
        //Don't forget to flush out
        BinaryStdOut.close();
    }
    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() throws Exception
    {
        int start = BinaryStdIn.readInt();
        String read = BinaryStdIn.readString();

        int[] next = new int[read.length()];
        int[] count = new int[R + 1];
        int n = read.length();

        for (int i = 0; i < n; i++) {
            count[read.charAt(i) + 1] = count[read.charAt(i) + 1] + 1;
        }

        for (int i = 0; i < R; i++) {
            count[i+1] = count[i] + count[i+1];
        }

        for (int i = 0; i < n; i++) {
            next[count[read.charAt(i)]++] = i;
        }

        for (int i = 0; i < read.length(); i++) {
            BinaryStdOut.write(read.charAt(next[start]));
            start = next[start];
        }
        BinaryStdOut.close();
    }
    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) throws Exception
    {
    	if (args[0].equals("-")) {
            //encode
            System.setIn(new ByteArrayInputStream("ABRACADABRA!".getBytes()));
            //System.setIn(new ByteArrayInputStream("41 42 52 41 43 41 44 41 42 52 41 21".getBytes()));
            encode();
        }
        else if (args[0].equals("+")) {
            //decode
            decode();
        }
    }
    //fuck java
    public static int[] toPrimitiveArray(ArrayList<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
class Suffix {
    public SuffixItem[] array;
    public String text;

    //public int[] text;

    private class SuffixItem  implements Comparable<SuffixItem> {
        private int index;

        public SuffixItem(int index) {
            this.index = index;
        }

        //Get Character in Array at index 'i'
        //private char getChar(int i) {
        private int getChar(int i) {
            //if ((i + index) < text.length) {
            if ((i + index) < text.length()) {
                return text.charAt(index + i);
                //return text[index + i];
            }
            else {
                //return text[(index + i) - text.length];
                return text.charAt((index + i) - text.length());
            }
        }

        private int getIndex() { return this.index; }

        //Required by implementing the Comparable interface
        //Just compares two SuffixItems
        //return -1 if other is greater than this
        //return 0 if both are equal
        //return 1 if this is greater than other
        public int compareTo(SuffixItem other) {
            //for (int i = 0; i < text.length; i++) {
            for (int i = 0; i < text.length(); i++) {
                if (this.getChar(i) > other.getChar(i)) {
                    return 1;
                }
                if (this.getChar(i) < other.getChar(i)) {
                    return -1;
                }
            }
            return 0;
        }
    }

    //public Suffix(int[] text) throws Exception {
    public Suffix(String text) throws Exception {
        if (text == null) {
            throw new Exception("Text is null!");
        }
        this.text = text;
        //this.array = new SuffixItem[text.length];
        this.array = new SuffixItem[text.length()];

        for (int i = 0; i < text.length(); i++) {
        //for (int i = 0; i < text.length; i++) {
            this.array[i] = new SuffixItem(i);
        }
        Arrays.sort(this.array);
    }
    /*public Suffix(String text) throws Exception {
        //Check for null
        if (text == null) {
            throw new Exception("Text is null!");
        }
        this.text = text;
        this.array = new SuffixItem[text.length()];
        //fill up array
        for (int i = 0; i < text.length(); i++) {
            this.array[i] = new SuffixItem(i);
        }
        Arrays.sort(this.array);
    }*/
    public int getIndex(int index) throws Exception {
        //if (index < 0 || index >= text.length) {
        if (index < 0 || index >= text.length()) {
            throw new Exception("Index out of bounds");
        }
        return array[index].getIndex();
    }
}