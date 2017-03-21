#ifndef _SYMBOL_HPP_
#define _SYMBOL_HPP_

#include <fstream>
#include <iostream>
#include <string>
#include <vector>

#include "key.hpp"

using namespace std;

class Symbol {
private:
	std::vector<Key> T;
	std::vector<Key> subT;

public:
	Symbol(const std::string&);
	void decrypt(const std::string&);
	std::string me;
	std::string encrypted;
	std::string table_filename;
};

class Table {
public:
	/*Table();
	void put();
	Key get();
	int size;

	inline Table() {

	}*/

};
class Value {
public:
	/*Value(Key value , Value *next);
	Value *next;
	Key value;
	bool hasNext();

	inline bool hasNext() {
		return next != null;
	}
	inline Value(Key value, Value *next) {
		this.value = value;
		this.next = next;
	}*/
};

#endif