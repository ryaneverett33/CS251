#include <cstdlib>
#include <fstream>
#include <iostream>
#include <string>
#include <stdio.h>

#include "key.hpp"
#include "brute.hpp"
#include "timer.hpp"

using namespace std;

std::string me;
std::string encrypted;
std::string table_filename;
bool verbose = false;

Brute::Brute(const std::string& filename) {
	T.resize(N);
	std::string buffer;
    std::fstream input(filename.c_str(), std::ios::in);
    for (int i = 0; i < N; i++) {
        std::getline(input, buffer);
        T[i].set_string(buffer);
    }
    input.close();
}

void Brute::decrypt(const std::string& encrypted){
	// your code here
	char possible[C];
	//char toUse[C];
	word_type toUse;
	Key keyToFind(encrypted);
	/*for (int i = 0; i < C; i++) {
		possible[i] = 0;
		toUse[i] = ALPHABET[0];
	}*/
	//R = size of the Alphabet (32) <key.hpp>
	//C = amount of characters (EX: "pass" C=4) <key.hpp>
	//cout << "Looping for: " << pow(R, C) << endl;
	for (int i = 0; i < pow(R, C); i++) {
		bool overflow = false;
		for (int j = (C - 1); j > -1; j--) {
			//fill possible array
			if (i == 0) {
				possible[j] = 0;
				toUse[j] = 0;
			}
			else if (j == (C - 1)) {
				possible[j] = possible[j] + 1;
				if (possible[j] == (R)) {
					possible[j] = 0;
					overflow = true;
				}
			}
			else {
				if (overflow) {
					possible[j] = possible[j] + 1;
					if (possible[j] == (R)) {
						possible[j] = 0;
						overflow = true;
					}
					else {
						overflow = false;
					}
				}
				else {
					possible[j] = possible[j];
				}
			}
			//toUse[j] = ALPHABET[possible[j]];
			toUse[j] = possible[j];
		}
		/*for (int j = 0; j < C; j++) {
			cout << toUse[j] << " ";
		}*/
		//cout << endl;
		//Key newKey((string(toUse)));
		Key newKey(false);
		newKey.m_digit = toUse;
		Key result = newKey.subset_sum(T, verbose);
		/*if (i == 5) {
			cout << "m_digit ";
			for (int x = 0; x < C; x++) {
				int foo = t[x];
				cout << foo << " ";
			}
			cout << endl;
			cout << "toUse ";
			for (int x = 0; x < C; x++) {
				int foo = toUse[x];
				cout << foo << " ";
			}
			cout << endl;
			char orig[C];
			for (int x = 0; x < C; x++) {
				orig[x] = ALPHABET[(int)toUse[x]];
			}
			cout << "toUse: " << string(orig) << endl;
		}*/
		//string encrypt = encrypted;
		//string usedString = newKey.getString();
		//string resultString = result.getString();
		if (result == keyToFind) {
			/*for (int x = 0; x < C; x++) {
				cout << toUse[x];
			}*/
			//look at result m_digit

			//Use the for loop instead of cout string(toUse) or cout toUse - Weird artifacts
			for (int i = 0; i < C; i++) {
				cout << ALPHABET[toUse[i]];
			}
			cout << endl;
		}
	}
}

int Brute::pow(int x, int y) {
	if (y == 0)
		return 1;
	else if (y % 2 == 0)
		return pow(x, y / 2)*pow(x, y / 2);
	else
		return x*pow(x, y / 2)*pow(x, y / 2);
}

void usage(const std::string& error_msg="") {
	if (!error_msg.empty()) std::cout << "ERROR: " << error_msg << '\n';
	std::cout << me << ": Brute force cracking of Subset-sum password with " 
		<< B << " bits precision\n"
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
	CPU_timer t;
	t.tic();
	
	//Code here
	/*if (encrypted.empty() || table_filename.empty() || me.empty()) {
		cout << "Empty strings!" << endl;
	}
	else {
		cout << "Hello World!" << endl;
	}*/
	Brute brute(table_filename);

	brute.decrypt(encrypted);
	t.toc();

	//Helpful for debugging
	//std::cout << "Elapsed: " << t.elapsed() << std::endl;

	return 0;
}