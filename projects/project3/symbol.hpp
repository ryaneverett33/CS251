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
	std::vector<Key> T;
	std::unordered_map<string, string> map;

public:
	Symbol(const std::string&);
	void decrypt(const std::string&);
	std::string me;
	std::string encrypted;
	std::string table_filename;
	int pow(int x, int y);
};

inline static string WordToString(word_type word) {
	std::string s = "";
	for (int foo = 0; foo < C; foo++) {
		s = s + ALPHABET[word[foo]];
	}
	return s;
}

#endif