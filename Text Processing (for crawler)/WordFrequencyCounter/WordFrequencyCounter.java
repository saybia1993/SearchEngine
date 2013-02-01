package ir.assignments.one.b;

import ir.assignments.one.a.Frequency;
import ir.assignments.one.a.Utilities;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;  // could I import this?

/**
 * Counts the total number of words and their frequencies in a text file.
 */
public final class WordFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private WordFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique word in the original list. The frequency of each word
	 * is equal to the number of times that word occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing frequency, with tied words sorted
	 * alphabetically.
	 * 
	 * The original list is not modified.
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["this", "sentence", "repeats", "the", "word", "sentence"]
	 * 
	 * The output list of frequencies should be 
	 * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of word frequencies, ordered by decreasing frequency.
	 */
	public static List<Frequency> computeWordFrequencies(List<String> words) {
		// classification below
		if (words == null) return null;
		ArrayList<Frequency> list =  new ArrayList<Frequency>(); // implement list by ArrayList, store each Frequency
		for(String s : words){ // iterate every input words
			int index =0;
			while (index != list.size()) {
				if (!s.equals(list.get(index).getText())) index++;
				else{  // find a match
					list.get(index).incrementFrequency(); // list already has the same word, only add frequency
					break;
				}
			}
			if (index == list.size()){  // no match in list
				Frequency f = new Frequency(s, 1); // create a new Frequency object
				list.add(f); // add into list
			}
		} // till now, every words with their occurrence frequency have been added to the ArrayList<Frequency>
		
		// sorting below:
		// put every words with same occurrence frequency into same array list
		ArrayList<ArrayList<Frequency>> ls = new ArrayList<ArrayList<Frequency>>();
		for (Frequency f : list){ // iterate list and take out every Frequency f
			int i=0;
			for (i=0; i<ls.size(); i++){ // iterate ls
				if (f.getFrequency() == ls.get(i).get(0).getFrequency()){ // same frequency array list already exist
					ls.get(i).add(f);  // add this Frequency f into that array list
					break; // for loop need to break
				}
			}
			if (i == ls.size()){ // no array list contains the same occurrence frequency
				ArrayList<Frequency> sameFrequencyList = new ArrayList<Frequency>(); // create a new array list	in array list ls	
				sameFrequencyList.add(f); // add the word with same occurrence frequency into this new array list (at index 0 position)
				ls.add(sameFrequencyList); // add this new array list into ls
			} 	
		}
		
		// sort each sameFrequencyList in ls by frequency decreasing order
		ArrayList<Frequency> temp = null;
		for(int i=0; i<ls.size()-1; i++){ // selection sort
			for(int j=i+1; j<ls.size(); j++){
				if (ls.get(j).get(0).getFrequency()>ls.get(i).get(0).getFrequency()){
					temp = ls.get(j);
					ls.set(j, ls.get(i)); // set ls's j position to ls.get(i)
					ls.set(i, temp);// set ls's i position to temp
				}
			}
		}

		// sort every words with same occurrence frequency in sameFrequencyList in alphabetical order
		for(ArrayList<Frequency> sameFrequency : ls){ // iterate every sameFrequencyList
			Frequency temp2 = null;
			for (int i=0; i<sameFrequency.size(); i++){ // selectionSort every sameFrequency in alphabetical order
				for (int j=i+1; j<sameFrequency.size(); j++){
					if (sameFrequency.get(j).getText().compareTo(sameFrequency.get(i).getText())<0 ){ // j<i
						temp2 = sameFrequency.get(j); // smaller one
						sameFrequency.set(j, sameFrequency.get(i)); // set bigger one to smaller one's position
						sameFrequency.set(i, temp2);
					}
				}
			}
		}
		
		// combine every sameFreuency into one List<Frequency>
		List<Frequency> reValue = new ArrayList<Frequency>();
		for (ArrayList<Frequency> sameFrequency: ls){ // iterate every sameFrequency
			reValue.addAll(sameFrequency);
		}
		return reValue;
	}


	/**
	 * Runs the word frequency counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		File file = new File(args[0]);
		List<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computeWordFrequencies(words);
		Utilities.printFrequencies(frequencies);
	}
}
