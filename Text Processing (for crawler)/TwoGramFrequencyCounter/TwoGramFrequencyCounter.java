package ir.assignments.one.c;

import ir.assignments.one.a.Frequency;
import ir.assignments.one.a.Utilities;
import ir.assignments.one.b.WordFrequencyCounter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Count the total number of 2-grams and their frequencies in a text file.
 */
public final class TwoGramFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private TwoGramFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique 2-gram in the original list. The frequency of each 2-grams
	 * is equal to the number of times that two-gram occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing frequency, with tied 2-grams sorted
	 * alphabetically. 
	 * 
	 * 
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["you", "think", "you", "know", "how", "you", "think"]
	 * 
	 * The output list of 2-gram frequencies should be 
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of two gram frequencies, ordered by decreasing frequency.
	 */
	private static List<Frequency> computeTwoGramFrequencies(ArrayList<String> words) {
		if (words == null) return null;
		ArrayList<Frequency> list = new ArrayList<Frequency>();  // implement list by ArrayList, store every two Frequency
		String previous = null;
		for (String s : words){
			if (previous == null){
				previous = s;
				continue;
			}
			int index = 0;
			while (index != list.size()) {  // iterate list		
				if (!(previous +" "+ s).equals(list.get(index).getText())){  // NOT match
					index++; // next object in list
				}
				else {       // match
					list.get(index).incrementFrequency();			
					previous = s;
					break;
				}
			}
			if (index == list.size()){    // while finished, not exist
				Frequency f = new Frequency(previous +" "+ s, 1);  // create a new Frequency object
				list.add(f);   // add into list
				previous = s;
			}
		}// till now, every 2-gram with their occurrence frequency have been added to the ArrayList<Frequency> list
		
		// sorting below:
		// put every  2-gram with same occurrence frequency into same array list
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
		
		// sort every 2-gram with same occurrence frequency in sameFrequencyList in alphabetical order
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
	 * Runs the 2-gram counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		File file = new File(args[0]);
		ArrayList<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computeTwoGramFrequencies(words);
		Utilities.printFrequencies(frequencies);
	}
}
