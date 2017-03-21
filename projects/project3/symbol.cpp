#include <cstdlib>
#include <fstream>
#include <iostream>
#include <string>

#include "key.hpp"
#include "symbol.hpp"

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
}

void Symbol::decrypt(const std::string& encrypted){
	// insert your code here
}

/*Table::get() {
	return NULL;
}*/

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
	
	// insert your code here
	
	return 0;
}
