#ifndef _SYMBOL_HPP_
#define _SYMBOL_HPP_

#include <fstream>
#include <iostream>
#include <string>
#include <vector>
#include <functional>
#include <unordered_map>

#include "key.hpp"

using namespace std;

class Symbol {
private:
	///use std::map instead of this shit
	
	std::unordered_map<std::string, std::string> map;
	//std::unordered_map<int, int> map;

public:
	std::vector<Key> T;
	Symbol(const std::string&);
	void decrypt(const std::string&);
	std::string me;
	std::string encrypted;
	std::string table_filename;
	int pow(int x, int y);
	void getString(int value, bool firstHalf, word_type &arrayOut);
	int fromWordType(word_type &value, bool firstHalf);
};
inline static string WordToString(word_type word) {
	std::string s = "";
	for (int foo = 0; foo < C; foo++) {
		s = s + ALPHABET[word[foo]];
	}
	return s;
}

//class StoredDetails

#endif