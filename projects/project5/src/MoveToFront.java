public class MoveToFront {
    static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        //Create the Object
        char[] withIndex = new char[R];
        char[] charIndex = new char[R];

		/*
        for (char i = 0; i < R; i++) {
			withIndex[i] = 0;
			charIndex[i[ = 0;
		 */

        //Initialize the Array
        for (char i = 0; i < R; i++) {
            withIndex[i] = i;
            charIndex[i] = i;
        }

        //Read From StdIn
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            char index = charIndex[c];

            BinaryStdOut.write(index);

            //shift(ind, c);
            for (char i = index; i > 0; i--) {
                char x = withIndex[i - 1];
                withIndex[i] = x;
                charIndex[x] = i;
            }
            charIndex[c] = 0;
            withIndex[0] = c;
        }

        //Cleanup
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        //Create the Object
        char[] withIndex = new char[R];
        char[] charIndex = new char[R];

		/*
		for (char i = 0; i < R; i++) {
			withIndex[i] = 0;
			charIndex[i[ = 0;
		 */

        //Initalize the Array
        for (char i = 0; i < R; i++) {
            withIndex[i] = i;
            charIndex[i] = i;
        }

        //Read From StdIn
        while (!BinaryStdIn.isEmpty()) {
            char index = BinaryStdIn.readChar();
            char c = withIndex[index];

            BinaryStdOut.write(c);
            
            /*
            int jmp = 0;
            for (char i = index; i > 0; i--) {
            	char foo = withIndex[Math.abs(i - jmp)];
            	jmp = jmp + 1;
            }
             */
            for (char i = index; i > 0; i--) {
                char foo = withIndex[i - 1];
                withIndex[i] = foo;
                charIndex[foo] = i;
            }
            charIndex[c] = 0;
            withIndex[0] = c;
        }

        //Cleanup
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("-")) {
                encode();
            } else if (args[0].equals("+")) {
                decode();
            }
        } else {
            System.out.println("No arguments supplied!");
        }
    }
}