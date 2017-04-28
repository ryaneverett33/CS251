public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, 
    //reading from standard input and writing to standard output
    //private BurrowsWheeler()
    //{}
    public static void encode()
    {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        
        int length = s.length();
        int original = 0;
        char[] code = new char[length];
        for (int i = 0; i < length; i++)
        {
            if (csa.index(i) == 0) original = i;
            code[i] = s.charAt((csa.index(i) -1 + length) % length);
        }
        BinaryStdOut.write(original);
        for (int i = 0; i < length; i++)
        {
            BinaryStdOut.write(code[i]);
        }
        BinaryStdOut.close();
    }

    private Letter[] kic(Letter[] a)
    {
        int R = 256;
        int N = a.length;
        Letter[] aux = new Letter[N];
        int[] count = new int[R+1];
        for (int i = 0; i < N; i++)
            count[a[i].ch+1]++;
        
        for (int r = 0; r < R; r++)
            count[r+1] += count[r];
        for (int i = 0; i < N; i++)
            aux[count[a[i].ch]++] = a[i];
        return aux;
    }
    
    private class Letter
    {
        private char ch;
        private int ind;
        public Letter(char c, int i)
        {
            this.ch = c;
            this.ind = i;
        }
    }
    private char[] decode(int start, String s)
    {
        int length = s.length();
        
        
        Letter[] input = new Letter[length];
        for (int i = 0; i < length; i++)
        {
            input[i] = new Letter(s.charAt(i), i);
        }
        Letter[] sorted = kic(input);
        int[] next = new int[length];
        
        for (int i = 0; i < length; i++)
        {
            next[i] = sorted[i].ind;
        }
        
        char[] text = new char[length];
        
        for (int i = 0; i < length; i++)
        {
            text[i] = sorted[start].ch;
            start = next[start];
        }
        
        return text;
    }
    
    
    
    // apply Burrows-Wheeler decoding, 
    //reading from standard input and writing to standard output
    public static void decode()
    {
        int start = BinaryStdIn.readInt();
        
        String s = BinaryStdIn.readString();
        
        BurrowsWheeler bw = new BurrowsWheeler();
        
        for (char c : bw.decode(start, s))
        {
            BinaryStdOut.write(c);
        }
        
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args)
    {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}

class CircularSuffixArray {
    private int[] indecies;
    private String str;
    private int length;

    public CircularSuffixArray(String s)  // circular suffix array of s
    {
        str = s;
        length = s.length();
        indecies = new int[length];

        Suffix[] suffixes = new Suffix[length];
        for (int i = 0; i < length; i++) {
            suffixes[i] = new Suffix(i);
        }
        java.util.Arrays.sort(suffixes);


        for (int i = 0; i < length; i++) {
            indecies[i] = suffixes[i].startAt;
        }
    }

    private class Suffix implements Comparable {
        private int startAt;

        public Suffix(int start) {
            this.startAt = start;
        }

        public char charAt(int position) {
            return str.charAt((position + startAt) % length);
        }

        public int compareTo(Object another) {
            Suffix that = (Suffix) another;
            for (int i = 0; i < length; i++) {
                char a = this.charAt(i);
                char b = that.charAt(i);
                if (a > b)
                    return 1;
                else if (a < b)
                    return -1;
            }
            return 0;

        }

    }


    public int length()                   // length of s
    {
        return length;
    }

    public int index(int i)               // returns index of ith sorted suffix
    {
        return indecies[i];
    }
}