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
	bool look = false;
	//fill plaintext
	for (int i = 0; i < C; i++) {
		plaintext[i] = 0;
	}
	for (int i = 0; i < len; i++) {
		Symbol::getString(i, true, plaintext);
		newKey.m_digit = plaintext;
		result = newKey.subset_sum(T, verbose);
		/*if (plaintext[0] == 15 && plaintext[1] == 0 && plaintext[2] == 18) {
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
			look = true;
		}*/
		//map.insert(result.getString(), WordToString(plaintext));
		//string resStr = result.getString();
		//string plainStr = WordToString(plaintext);
		//map[result.getString()] = WordToString(plaintext);
		map[Symbol::fromWordType(result.m_digit, true)] = i;
		/*if (look) {
			string exists = map[result.getString()];
			if (exists.empty()) {
				cout << "Doesn't exist in table" << endl;
			}
			else {
				cout << "Exists in table" << endl;
			}
			look = false;
		}*/
	}
	//cout << "Finished first loop" << endl;
	if (C % 2 != 0) {
		half = half - 1;
	}
	len = pow(R, half);
	/*cout << "Searching " << endl;
	auto foo = map.find("jfwp0");
	if (foo != map.end()) {
		cout << "Search found in map: " << endl;
		cout << "Key: " << foo->first << endl;
		cout << "Value: " << foo->second << endl;
	}*/

	//reset plaintext
	for (int foo = 0; foo < C; foo++) {
		plaintext[foo] = 0;
	}

	for (int i = 0; i < len; i++) {
		Symbol::getString(i, false, plaintext);
		/*for (int j = (C - 1); (j > half - 1); j--) {
			if (i == 0) {
				plaintext[j] = 0;
				continue;
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
		}*/
		/*for (int s = 0; s < C; s++) {
			cout << ALPHABET[plaintext[s]];
		}
		cout << endl;*/
		newKey.m_digit = plaintext;
		result = newKey.subset_sum(T, verbose);
		/*if (plaintext[3] == 18 && plaintext[4] == 22) {
			cout << "pass other half" << endl;
			for (int q = 0; q < C; q++) {
				cout << ALPHABET[result.m_digit[q]];
			}
			cout << endl;
		}*/
		Key diff = toFind - result;
		int diffAsInt = Symbol::fromWordType(diff.m_digit, false);
		auto search = map.find(diffAsInt);
		if (search != map.end()) {
			string resultAgain = diff.getString();
			//cout << "Found!" << endl;
			//cout << "First: " << search->first << endl;
			//cout << "Second: " << search->second << endl;
			Key conv(false);
			Symbol::getString(search->second, true, conv.m_digit);
			Key combined = newKey + conv;							//CONVERT HERE
			//cout << "Combined: " << combined.getString() << endl;
			cout << combined.getString() << endl;
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
	//cout << "Finished second loop" << endl;
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
	//cout << "Initialize" << endl;
	Symbol sym(table_filename);
	//cout << "Created" << endl;
	sym.decrypt(encrypted);
	//cout << "Decrypted" << endl;

	//word_type blah = { 26, 23, 28, 17, 10, 0, 0, 0, 0, 0};
	//word_type blah = {0, 0, 0, 0, 0, 15, 31, 31, 31, 31};
	//cout << "Int: " << sym.fromWordType(blah, false) << endl;
	
	/*Key firstHalf = Key(string("pasaa")).subset_sum(sym.T, verbose);
	Key secondHalf = Key(string("aaasw")).subset_sum(sym.T, verbose);
	Key encKey(encrypted);

	cout << "Encrypted: " << encrypted << endl;
	cout << "Encrypted First Half: " << firstHalf.getString() << endl;
	cout << "Encrypted Second Half: " << secondHalf.getString() << endl;

	Key keyFirstSecond = firstHalf + secondHalf;
	cout << "First Half + Second Half: " << keyFirstSecond.getString() << endl;
	Key keySecondFirst = secondHalf + firstHalf;
	cout << "Second Half + First Half: " << keySecondFirst.getString() << endl;
	Key encMinusFirst = encKey - firstHalf;
	Key encMinusLast = encKey - secondHalf;
	cout << "Encrypted - First Half: " << encMinusFirst.getString() << endl;
	cout << "Encrypted - Second Half: " << encMinusLast.getString() << endl;*/


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

	/*Key a(false);
	a.m_digit = { 1,1,1,1,1 };
	Key res = a + a;
	for (int i = 0; i < C; i++) {
		cout << (int)res.m_digit[i];
	}
	cout << endl;
	Key full(false);
	full.m_digit = { R, R, R, R, R };
	res = full + full;
	for (int i = 0; i < C; i++) {
		cout << ALPHABET[res.m_digit[i]];
	}
	cout << endl;*/

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
void Symbol::getString(int value, bool firstHalf, word_type &arrayOut) {
	int half = C / 2;
	if (C % 2 != 0)
		half = half + 1;
	int currValue = value;
	int startIndex = 0, finishIndex = 0;
	if (firstHalf) {
		startIndex = (half -1 );
		finishIndex = -1;
	}
	else {
		startIndex = (C - 1);
		finishIndex = (half - 1);
	}
	for (int j = startIndex; j > finishIndex; j--) {
		if (currValue < R) {
			arrayOut[j] = currValue;
		}
		else {
			arrayOut[j] = currValue % R;
			currValue = currValue / R;
		}
	}
}
int Symbol::fromWordType(word_type &value, bool firstHalf) {
	int half = C / 2;
	if (C % 2 != 0)
		half = half + 1;
	int startIndex = 0, finishIndex = 0;
	int sum = 0;
	if (firstHalf) {
		startIndex = (half -1 );
		finishIndex = -1;
	}
	else {
		startIndex = (C - 1);
		finishIndex = -1;
	}
	for (int j = startIndex; j > finishIndex; j--) {
		int jInverse = 0;
		if (firstHalf) {
			jInverse = (half - j) - 1;
		}
		else {
			jInverse = (C - j) - 1;
		}
		if (value[j] == 0) {
			continue;
		}
		sum = sum + (value[j] * Symbol::pow(R, jInverse));
	}
	return sum;
}