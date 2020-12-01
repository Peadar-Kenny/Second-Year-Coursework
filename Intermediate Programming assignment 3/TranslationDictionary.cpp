/************* TranslationDictionary.cpp **********************/
#include <string>
#include <iostream>
#include <fstream>

#include "TranslationDictionary.h"

using namespace std;

Trans_Dict::Trans_Dict() {}

/*************************************************************************/
/* builds dictionary from file with translation pairs source/translation */
/* on single lines                                                       */
/*************************************************************************/
Trans_Dict::Trans_Dict(string file_name) {
  string line, translation, word;
  ifstream f;
  Transpair p;
  size_t trans_offset;


  f.open(file_name);
  if(f) {
    opened_ok = true;
  }
  else {
    opened_ok = false;
  }
    
  if(opened_ok) {

    while(f >> line) {
      trans_offset = line.find('/'); /* trans_offset is location of / */

      if(trans_offset == string::npos) {
	cout << "there was an entry with no /\n";
	continue;
      }
      else {
	word = line.substr(0,trans_offset); // up to / 
	translation = line.substr(trans_offset+1); // after / 
	// make transpair with word and translation
	p.french = word;
	p.english = translation;
	thewords.push_back(p); // add transpair to thewords
      }

    }
  }

}


/*****************************************************************/
/* looks up word in dictionary, returning true or false          */
/* setting 'trans' to contain the translation if found 
/*****************************************************************/
bool Trans_Dict::lookup(string word, string& trans) {
  int i;
  for(i = 0; i < thewords.size(); i++) {
    if (thewords[i].french == word) {
      trans = thewords[i].english;
      return true;
    }
  }
  return false;
}






