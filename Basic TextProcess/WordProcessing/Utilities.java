package ir.assignments.one.a;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of utility methods for text processing.
 */
public class Utilities {
	/**
	 * Reads the input text file and splits it into alphanumeric tokens.
	 * Returns an ArrayList of these tokens, ordered according to their
	 * occurrence in the original text file.
	 * 
	 * Non-alphanumeric characters delineate tokens, and are discarded.
	 *
	 * Words are also normalized to lower case. 
	 * 
	 * Example:
	 * 
	 * Given this input string
	 * "An input string, this is! (or is it?)"
	 * 
	 * The output list of strings should be
	 * ["an", "input", "string", "this", "is", "or", "is", "it"]
	 * 
	 * @param input The file to read in and tokenize.
	 * @return The list of tokens (words) from the input file, ordered by occurrence.
	 */
	public static ArrayList<String> tokenizeFile(File input) {
			ArrayList<String> tokenList = new ArrayList<String>(); // create an array list to store the tokens
			try {    // try to read 
				BufferedReader rd = new BufferedReader(new FileReader(input.getPath()));
				String eachLine;
				eachLine = rd.readLine(); // assign eachLine first value
				while(eachLine != null) {
				     String[] temp = eachLine.split(" "); // split each line into tokens and store them in String[]
				     for (int x=0; x<temp.length; x++) { 
				    	 tokenList.add(temp[x]);// add each token into array list
				     } 
				     eachLine = rd.readLine(); // read next line
				}
				tokenList = deleteSymbol(tokenList);
			}
			catch(IOException ex){
				System.out.println("File doesn't exist");
			}
		return tokenList;
	}
		
	// method, delete each String's symbol (like: "!", "?", ",", ")", "(" )
	public static ArrayList<String> deleteSymbol(ArrayList<String> a) {
		ArrayList<String> temp = new ArrayList<String>();
		for(String s : a) {
			s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase(); // replace all the other symbol except a-z, A-Z, 0-9
			temp.add(s);
		}
		return temp;
	}
	
	/**
	 * Takes a list of {@link Frequency}s and prints it to standard out. It also
	 * prints out the total number of items, and the total number of unique items.
	 * 
	 * Example one:
	 * 
	 * Given the input list of word frequencies
	 * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
	 * 
	 * The following should be printed to standard out
	 * 
	 * Total item count: 6
	 * Unique item count: 5
	 * 
	 * sentence	2
	 * the		1
	 * this		1
	 * repeats	1
	 * word		1
	 * 
	 * 
	 * Example two:
	 * 
	 * Given the input list of 2-gram frequencies
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 * 
	 * The following should be printed to standard out
	 * 
	 * Total 2-gram count: 6
	 * Unique 2-gram count: 5
	 * 
	 * you think	2
	 * how you		1
	 * know how		1
	 * think you	1
	 * you know		1
	 * 
	 * @param frequencies A list of frequencies.
	 */
	public static void printFrequencies(List<Frequency> frequencies) {
		int total = 0;
		int unique = 0;
		for(Frequency f : frequencies) {  // iterate every frequency
			unique = unique + 1;
			total = total + f.getFrequency();
			System.out.println(f.toString());
		}
		System.out.println("Total items:  " + total);
		System.out.println("Unique items:  " + unique);
	}

}

