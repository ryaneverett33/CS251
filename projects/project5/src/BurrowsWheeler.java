import java.util.*;

public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {
        String read = BinaryStdIn.readString();
        SuffixItemArray SuffixItemArr = new SuffixItemArray(read);

        int length = read.length();
        int original = 0;
        char[] arr = new char[length];
        for (int i = 0; i < length; i++) {
            if (SuffixItemArr.getIndex(i) == 0) original = i;
            arr[i] = read.charAt((SuffixItemArr.getIndex(i) - 1 + length) % length);
        }
        BinaryStdOut.write(original);
        for (int i = 0; i < length; i++) {
            BinaryStdOut.write(arr[i]);
        }
        BinaryStdOut.close();
    }

    static Node[] sortArr(Node[] a) {
        int R = 256;
        int N = a.length;
        Node[] aux = new Node[N];
        int[] count = new int[R + 1];
        for (int i = 0; i < N; i++) {
            count[a[i].c + 1]++;
        }

        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }
        for (int i = 0; i < N; i++) {
            aux[count[a[i].c]++] = a[i];
        }
        return aux;
    }

    static class Node {
        private char c;
        private int index;

        public Node(char c, int i) {
            this.c = c;
            this.index = i;
        }
    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    private char[] decode(int start, String s) {
        int length = s.length();

        Node[] input = new Node[length];
        for (int i = 0; i < length; i++) {
            input[i] = new Node(s.charAt(i), i);
        }
        Node[] sorted = sortArr(input);
        int[] next = new int[length];

        for (int i = 0; i < length; i++) {
            next[i] = sorted[i].index;
        }

        char[] text = new char[length];

        for (int i = 0; i < length; i++) {
            text[i] = sorted[start].c;
            start = next[start];
        }

        return text;
    }


    // apply Burrows-Wheeler decoding, 
    //reading from standard input and writing to standard output
    public static void decode() {
        int start = BinaryStdIn.readInt();

        String read = BinaryStdIn.readString();

        Node[] input = new Node[read.length()];
        for (int i = 0; i < read.length(); i++) {
            input[i] = new Node(read.charAt(i), i);
        }
        Node[] sorted = sortArr(input);
        int[] next = new int[read.length()];

        for (int i = 0; i < read.length(); i++) {
            next[i] = sorted[i].index;
        }

        char[] text = new char[read.length()];

        for (int i = 0; i < read.length(); i++) {
            text[i] = sorted[start].c;
            start = next[start];
        }

        /*for (char c : bw.decode(start, s)) {
            BinaryStdOut.write(c);
        }*/

        BinaryStdOut.close();
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("-")) {
                encode();
            } else if (args[0].equals("+")) {
                decode();
            }
        }
        else {
            System.out.println("No arguments supplied!");
        }
    }
}

class SuffixItemArray {
    private int[] indexexes;
    private String value;
    private int length;

    public SuffixItemArray(String value)  // circular SuffixItem array of s
    {
        this.value = value;
        this.length = value.length();
        this.indexexes = new int[this.length];

        SuffixItem[] suffixItem = new SuffixItem[length];
        for (int i = 0; i < length; i++) {
            suffixItem[i] = new SuffixItem(i);
        }
        Arrays.sort(suffixItem);


        for (int i = 0; i < length; i++) {
            indexexes[i] = suffixItem[i].start;
        }
    }

    private class SuffixItem implements Comparable<SuffixItem> {
        private int start;

        public SuffixItem(int start) {
            this.start = start;
        }

        public char charAt(int pos) {
            return value.charAt((pos + start) % length);
        }

        public int compareTo(SuffixItem that) {
            //If we're a copy of ourselves, return true
            if (this == that) {
                return 0;
            }
            for (int i = 0; i < length; i++) {
                char a = this.charAt(i);
                char b = that.charAt(i);
                if (a > b) {
                    return 1;
                } else if (a < b) {
                    return -1;
                }
            }
            return 0;

        }

    }


    public int length()                   // length of s
    {
        return length;
    }

    public int getIndex(int i) {
        return indexexes[i];
    }
}