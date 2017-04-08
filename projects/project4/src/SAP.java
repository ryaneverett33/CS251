/**
 * Created by Ryan on 4/7/2017.
 */
import java.util.*;
public class SAP {
    //don't use algs.jar
    Digraph graph;
    int lastV, lastW, lastLength, lastAncestor;
    int lastVi, lastWi;

    // constructor
    public SAP(Digraph G) {
        // Use the Digraph implementation provided by the book
        graph = G;
        lastV = -1;
        lastW = -1;
        lastVi = -1;
        lastWi = -1;
        lastAncestor = -1;
        lastLength = -1;
    }
    // return length of shortest ancestral path of v and w; -1 if no such path
    public int length(int v, int w) {
        //Check if v and w are valid before continuing
        checkInput(v);
        checkInput(w);

        //If we've already calculated it, return the previous result
        if (lastV == v && lastW == w) {
            return lastLength;
        }
        else if (lastV == w && lastW == v) {
            //Consider the case where the inputs are backwards but still have the same length
            return lastLength;
        }
        //do BreadFirstSearch
        bfs(v, w);

        return lastLength;
    }
    // return a common ancestor of v and w that participates in a shortest
// ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        //Similar to length(), check for valid inputs before using
        checkInput(v);
        checkInput(w);

        //This is basically just a modification of length()
        //If we've already calculated it, return the previous result
        if (lastV == v && lastW == w) {
            return lastAncestor;
        }
        else if (lastV == w && lastW == v) {
            //Consider the case where the inputs are backwards but still have the same length
            return lastAncestor;
        }
        //do BreadFirstSearch
        bfs(v, w);

        return lastAncestor;
    }
    private void checkInput(int vertex) {
        if (vertex < 0 || vertex > (graph.V() - 1)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }
    private void bfs(int v, int w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
        int currLen = Integer.MAX_VALUE;
        int currAnc = -1;
        for (int vert = 0; vert < graph.V(); vert++) {
            if (bfsV.hasPathTo(vert) && bfsW.hasPathTo(vert)) {
                //Split this up to make it easier for reading
                int lenToVert = bfsV.distTo(vert);
                lenToVert = lenToVert + bfsW.distTo(vert);
                if (lenToVert < currLen) {
                    //We found a shorter path, store it instead
                    currLen = lenToVert;
                    currAnc = vert;
                }
            }
        }
        //Check if we couldn't find any paths
        if (currLen == Integer.MAX_VALUE) {
            currLen = -1;
        }

        //Store computed values
        this.lastV = v;
        this.lastW = w;
        this.lastVi = -1;
        this.lastWi = -1;
        this.lastLength = currLen;
        this.lastAncestor = currAnc;
    }
    public static void main(String[] args) throws Exception {
        //takes digraph input file as arg0 and digraph test input file as arg1
        //java SAP digraph1.txt digraph1.input
        if (args.length < 2) {
            throw new Exception("Too many arguments");
        }
        In graphIn = new In(args[0]);
        Digraph graph = new Digraph(graphIn);
        SAP sap = new SAP(graph);
        In testIn = new In(args[1]);
        while (!testIn.isEmpty()) {
            int v = testIn.readInt();
            int w = testIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            System.out.printf("sap = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
