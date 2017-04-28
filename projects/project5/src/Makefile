BurrowsWheeler : BurrowsWheeler.java stdlib.jar algs4.jar
	javac -cp .:stdlib.jar:algs4.jar BurrowsWheeler.java
	
MoveToFront : MoveToFront.java stdlib.jar algs4.jar
	javac -cp .:stdlib.jar:algs4.jar MoveToFront.java

HexDump : HexDump.java stdlib.jar algs4.jar	
	javac -cp .:stdlib.jar HexDump.java
	
Huffman : Huffman.java stdlib.jar algs4.jar
	javac -cp .:stdlib.jar:algs4.jar Huffman.java
	
all : BurrowsWheeler.java MoveToFront.java HexDump.java Huffman.java
	javac -cp .:stdlib.jar:algs4.jar BurrowsWheeler.java
	javac -cp .:stdlib.jar:algs4.jar MoveToFront.java
	javac -cp .:stdlib.jar HexDump.java
	javac -cp .:stdlib.jar:algs4.jar Huffman.java
	
clean :
	rm BurrowsWheeler.class MoveToFront.class HexDump.class Huffman.class