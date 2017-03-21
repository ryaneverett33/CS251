#ifndef _BRUTE_HPP_
#define _BRUTE_HPP_

#include <fstream>
#include <iostream>
#include <string>

#include "key.hpp"

class Brute {
private:
	std::vector<Key> T;
	int pow(int x, int y);

public:
	Brute(const std::string&);
	void decrypt(const std::string&);
	std::string me;
	std::string encrypted;
	std::string table_filename;
	bool verbose = false;
};

#endif
