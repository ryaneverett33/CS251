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
	//int c = C;
	word_type plaintext;
	Key toFind(encrypted);
	Key newKey(false);
	Key result(false);
	//fill plaintext
	for (int i = 0; i < C; i++) {
		plaintext[i] = 0;
	}
	for (int i = 0; i < len; i++) {
		bool overflow = false;
		for (int j = (half - 1); j > -1; j--) {
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
		result = newKey.subset_sum(T, verbose);
		if (plaintext[0] == 15 && plaintext[1] == 0 && plaintext[2] == 18) {
			cout << "pass half" << endl;
			for (int x = 0; x < C; x++) {
				cout << ALPHABET[plaintext[x]];
			}
			cout << endl;
			cout << "Result of pass encrypt:" << endl;
			for (int x = 0; x < C; x++) {
				cout << ALPHABET[result.m_digit[x]];
			}
			cout << endl;
		}
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
	cout << "Searching " << endl;
	auto foo = map.find("exvaa");
	if (foo != map.end()) {
		cout << foo->first << endl;
		cout << foo->second << endl;
	}
	for (int i = 0; i < len; i++) {
		bool overflow = false;
		for (int j = (C - 1); (j > half - 1); j--) {
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
		/*for (int s = 0; s < C; s++) {
			cout << ALPHABET[plaintext[s]];
		}
		cout << " ";
		newKey.m_digit = plaintext;
		result = newKey.subset_sum(T, verbose);
		for (int s = 0; s < C; s++) {
			cout << ALPHABET[result.m_digit[s]];
		}
		cout << endl;*/
		Key sub = toFind - result;
		if (sub.getString() == "jfwp0") {
			cout << "HOLY SHIT" << endl;
		}
		Key diff = toFind - result;
		auto search = map.find(diff.getString());
		if (search != map.end()) {
			string resultAgain = diff.getString();
			cout << "Found!" << endl;
			cout << "First: " << search->first << endl;
			cout << "Second: " << search->second << endl;
			Key blah = search->second;
			blah += diff;
			cout << "Blah: " << blah.getString() << endl;
		}
	}
	//try and find in map
	/*auto mapFind = map.find("jfwp0");
	if (mapFind != map.end()) {
		cout << "Found passw encrypted" << endl;
		Key toSub = Key(mapFind->second);
		Key encCopy = toFind - toSub;
		cout << "Subtracted value" << endl;
		for (int jack = 0; jack < C; jack++) {
			cout << ALPHABET[encCopy.m_digit[jack]];
		}
		cout << endl;
		/*cout << "First: " << mapFind->first << endl;
		cout << "Second: " << mapFind->second << endl;*/
	/*}
	else {
		cout << "Could not find passw encrypted" << endl;
	}*/
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
	/*Key b(string("bbbbb"));
	Key a(string("aaaaa"));

	Key sub = b - a;
	cout << "Sub: " << sub.getString() << endl;

	a = Key(string("bbbbb"));

	sub = b - a;
	cout << "Sub; " << sub.getString() << endl;

	b = Key(string("cbccc"));
	a = Key(string("bcbbb"));

	sub = b - a;
	cout << "Sub: " << sub.getString() << endl;*/
	
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