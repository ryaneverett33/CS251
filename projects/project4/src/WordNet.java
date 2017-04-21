/**
 * Created by Ryan on 4/7/2017.
 */
import java.util.*;
public class WordNet {
    //Don't use algs.jar
    private Map<Integer, String> id2SynsetDefinition;
    private Map<String, Bag<Integer>> synset2id;

    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        id2SynsetDefinition = new HashMap<>();
        synset2id = new HashMap<>();
        //Load synsets
        In synIn = new In(synsets);
        while (synIn.hasNextLine()) {
            //synset is a csv file
            //id, word & synonyms, definition
            String currLine = synIn.readLine();
            String[] currSplit = currLine.split(",");
            int id = Integer.parseInt(currSplit[0]);
            String definition = currSplit[1] + ',' + currSplit[2];
            id2SynsetDefinition.put(id, definition);

            String synonyms[] = currSplit[1].split(" ");
            for (int i = 0; i < synonyms.length; i++) {
                Bag<Integer> bag = synset2id.get(synonyms[i]);
                if (bag == null) {
                    Bag<Integer> newBag = new Bag<>();
                    newBag.add(id);
                    synset2id.put(synonyms[i], newBag);
                }
                else {
                    bag.add(id);
                }
            }
        }
        In hyperIn = new In(hypernyms);
        Digraph graph = new Digraph(id2SynsetDefinition.size());
        while (hyperIn.hasNextLine()) {
            //csv #id,
            String currLine = hyperIn.readLine();
            String[] values = currLine.split(",");
            int rootEdge = Integer.parseInt(values[0]);
            if (rootEdge == 81426) {
                continue;
            }
            for (int i = 0; i < values.length; i++) {
                graph.addEdge(rootEdge, Integer.parseInt(values[i]));
            }
        }
        sap = new SAP(graph);
    }
    // is the word a WordNet noun? This can be used to search for existing
// nouns at the beginning of the printSap method
    public boolean isNoun(String word) {
        return synset2id.containsKey(word);
    }
    // print the synset (second field of synsets.txt) that is the common ancestor
// of nounA and nounB in a shortest ancestral path as well as the length of the path,
// following this format: "sap<space>=<space><number>,<space>ancestor<space>=<space><synsettext>"
// If no such path exists the sap should contain -1 and ancestor should say "null"
// This method should use the previously defined SAP datatype
    public void printSap(String nounA, String nounB) throws Exception {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            //throw new java.lang.Exception("WordNet does not contains a requested noun");
            System.out.println("sap = -1, ancestor = null");
            return;
        }
        int id = sap.ancestor(synset2id.get(nounA), synset2id.get(nounB));
        String definition = id2SynsetDefinition.get(id);
        //strip word from definition
        String defWord = definition.substring(0, definition.indexOf(','));
        int length = sap.length(synset2id.get(nounA), synset2id.get(nounB));
        System.out.printf("sap = %d, ancestor = %s\n", length, defWord);
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            throw new Exception("Too few arguments");
        }
        WordNet net = new WordNet(args[0], args[1]);
        In wordIn = new In(args[2]);
        while (wordIn.hasNextLine()) {
            String currLine = wordIn.readLine();
            String[] currSplit = currLine.split(" ");
            net.printSap(currSplit[0], currSplit[1]);
        }
    }
}
