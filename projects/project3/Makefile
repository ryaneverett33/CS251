brute : brute.cpp key.hpp timer.hpp brute.hpp
	g++ -std=c++11 -DUSE_$(CHARS)_CHARS -O2 brute.cpp -o brute

symbol : symbol.cpp key.hpp symbol.hpp
	g++ -std=c++11 -DUSE_$(CHARS)_CHARS -O2 symbol.cpp -o symbol

encrypt : encrypt.cpp
	g++ -std=c++11 -DUSE_$(CHARS)_CHARS -O2 encrypt.cpp -o encrypt

clean :
	rm brute symbol encrypt

all : brute symbol encrypt
