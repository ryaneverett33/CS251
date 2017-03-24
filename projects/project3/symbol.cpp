#include <cstdlib>
#include <fstream>
#include <iostream>
#include <string>

#include "key.hpp"
#include "symbol.hpp"

std::string me;
std::string encrypted;
std::string table_filename;
bool verbose = false;

Symbol::Symbol(const std::string& filename) {
	T.resize(N);
	std::string buffer;
    std::fstream input(filename.c_str(), std::ios::in);
    for (int i = 0; i < N; i++) {
        std::getline(input, buffer);
        T[i].set_string(buffer);
    }
    input.close();
	
	// insert your code here
	//initalize map
	int half = C / 2;
	if (C % 2 != 0)
		half = half + 1;
}

void Symbol::decrypt(const std::string& encrypted) {
	// insert your code here
	int half = C / 2;
	if (C % 2 != 0)
		half = half + 1;
	int len = pow(R, half);
	word_type plaintext;
	Key toFind(encrypted);
	Key newKey(false);
	//fill plaintext
	for (int i = 0; i < C; i++) {
		plaintext[i] = 0;
	}
	for (int i = 0; i < len; i++) {
		bool overflow = false;
		for (int j = (half - 1); j > -1; j--) {
			/*if (plaintext[1] == R)
				int five = 2;*/
			if (i == 0) {
				plaintext[j] = 0;
			}
			else if (j == (half - 1)) {
				plaintext[j] = plaintext[j] + 1;
				if (plaintext[j] == R) {
					plaintext[j] = 0;
					overflow = true;
				}
			}
			else {
				if (overflow) {
					plaintext[j] = plaintext[j] + 1;
					if (plaintext[j] == (R)) {
						plaintext[j] = 0;
						overflow = true;
					}
					else {
						overflow = false;
					}
				}
				else {
					continue;
				}
			}
		}
		newKey.m_digit = plaintext;
		Key result = newKey.subset_sum(T, verbose);
		//map.insert(result.getString(), WordToString(plaintext));
		//string resStr = result.getString();
		//string plainStr = WordToString(plaintext);
		map[result.getString()] = WordToString(plaintext);
	}
	cout << "Finished first loop" << endl;
	if (C % 2 != 0) {
		half = half - 1;
	}
	len = pow(R, half);
	for (int i = 0; i < len; i++) {
		bool overflow = false;
		for (int j = (C - 1); (j > half - 1); j--) {
			/*if (plaintext[1] == R)
			int five = 2;*/
			if (i == 0) {
				plaintext[j] = 0;
			}
			else if (j == (C - 1) && !overflow) {
				plaintext[j] = plaintext[j] + 1;
				if (plaintext[j] == R) {
					plaintext[j] = 0;
					overflow = true;
				}
				continue;
			}
			else {
				if (overflow) {
					plaintext[j] = plaintext[j] + 1;
					if (plaintext[j] == (R)) {
						plaintext[j] = 0;
						overflow = true;
					}
					else {
						overflow = false;
					}
					continue;
				}
				else {
					continue;
				}
			}
		}
	}
	cout << "Finished second loop" << endl;
	int five = 5;
}

void usage(const std::string& error_msg="") {
	if (!error_msg.empty()) std::cout << "ERROR: " << error_msg << '\n';
	std::cout << me << ": Symbol table-based cracking of Subset-sum password"
		<< " with " << B << " bits precision\n"
	    << "USAGE: " << me << " <encrypted> <table file> [options]\n"
		<< "\nArguments:\n"
		<< " <encrypted>:   encrypted password to crack\n"
		<< " <table file>:  name of file containing the table to use\n"
		<< "\nOptions:\n"
		<< " -h|--help:     print this message\n"
		<< " -v|--verbose:  select verbose mode\n\n";
	exit(0);
}

void initialize(int argc, char* argv[]) {
	me = argv[0];
	if (argc < 3) usage("Missing arguments");
	encrypted = argv[1];
	table_filename = argv[2];
	for (int i=3; i<argc; ++i) {
		std::string arg = argv[i];
		if (arg == "-h" || arg == "--help") usage();
		else if (arg == "-v" || arg == "--verbose") verbose = true;
		else usage("Unrecognized argument: " + arg);
	}
}


int main(int argc, char *argv[]){
	
	initialize(argc, argv);
	cout << "Initialize" << endl;
	Symbol sym(table_filename);
	cout << "Created" << endl;
	sym.decrypt(encrypted);
	cout << "Decrypted" << endl;
	
	// insert your code here
	/*int* blah;
	scanf("%d", blah);*/
	return 0;
}

int Symbol::pow(int x, int y) {
	if (y == 0)
		return 1;
	else if (y % 2 == 0)
		return pow(x, y / 2)*pow(x, y / 2);
	else
		return x*pow(x, y / 2)*pow(x, y / 2);
}