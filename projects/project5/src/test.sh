#!/bin/bash

make clean
make all

#Check BurrowsWheeler
java -cp .:stdlib.jar BurrowsWheeler - < abra.txt | java -cp .:stdlib.jar:algs4.jar BurrowsWheeler + > abraout1.txt
diff abraout1.txt abra.txt.mtf

java -cp .:stdlib.jar BurrowsWheeler - < abra.txt | java -cp .:stdlib.jar:algs4.jar BurrowsWheeler + > abraout2.txt
diff abraout2.txt abra.txt

#Check MoveToFront
java -cp .:stdlib.jar MoveToFront - < abra.txt > abraout1.txt.mtf
diff abraout1.txt.mtf abra.txt.mtf