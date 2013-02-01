package ir.assignments.one.d;

import ir.assignments.one.a.Frequency;
import ir.assignments.one.a.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class PalindromeFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private PalindromeFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique palindrome found in the original list. The frequency of each palindrome
	 * is equal to the number of times that palindrome occurs in the original list.
	 * 
	 * Palindromes can span sequential words in the input list.
	 * 
	 * The returned list is ordered by decreasing size, with tied palindromes sorted
	 * by frequency and further tied palindromes sorted alphabetically. 
	 * 
	 * The original list is not modified.
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["do", "geese", "see", "god", "abba", "bat", "tab"]
	 * 
	 * The output list of palindromes should be 
	 * ["do geese see god:1", "bat tab:1", "abba:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of palindrome frequencies, ordered by decreasing frequency.
	 */
	private static List<Frequency> computePalindromeFrequencies(ArrayList<String> words) {
			List<Frequency> freq = new ArrayList<Frequency>();
			String tmp = new String();//store string without whitepace
			String tmp1 = new String();//store original strings
			int i = 0;
			int j = 0;
			int k = 0;
			int m = 0;
			boolean exist = false;
			if(!words.isEmpty()) { //arraylist is not empty
				for(i=0; i < words.size(); i++) { //indicate the start of the string needed to be checked
					for(j=i; j<words.size(); j++) {  //indicate the end of the string needed to be checked
						for(m=i;m<=j;m++) {
							tmp = tmp.concat(words.get(m));//storing string without spaces
							if(m == j)
							tmp1 = tmp1.concat(words.get(m)); //storing the original string
							else 
							tmp1 = tmp1.concat(words.get(m)+" ");
						}
						if(isPal(tmp)) { //the string is palindrome
							if(freq.size()!=0) { //check if the arraylist<Frequency> is empty
								for(k=0; k<freq.size();k++) {//not empty, then iterate the whole list to find whether the palindrome has been added. 
									if( freq.get(k).getText().equals(tmp1) ) {//if already added, then increasing the frequency.
										freq.get(k).incrementFrequency();
										exist = true;
										if(exist == true)
										break;
									}
								}
								if(exist == false) { //the found palindrome has not been added
									Frequency f = new Frequency(tmp1,1); //add it in the arraylist
									freq.add(f);
								}
								tmp = "";//after added, clear the temp string
								tmp1 = "";
							}
							else { //if the arraylist is empty
								Frequency f = new Frequency(tmp1,1); //add it to the list
								freq.add(f);
								tmp="";	
								tmp1="";
							}	
						}
						tmp = "";
						tmp1 = "";
			        }
				}
			}
			ComparatorString comp = new ComparatorString(); //sort the list based on frequency
			Collections.sort(freq, comp);
			return freq;
	}
	
	private static boolean isPal(String s) { //check the string to see if it is palindrome
		StringBuffer tmp = new StringBuffer(s);
		return tmp.reverse().toString().equals(s);
	}
	
	/**
	 * Runs the 2-gram counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		File file = new File(args[0]);
		ArrayList<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computePalindromeFrequencies(words);
		Utilities.printFrequencies(frequencies);
	}
}
