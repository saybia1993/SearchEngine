package ir.assignments.two;

import java.util.Collection;

public class Crawler {
	
	public static void main(String[] args) throws Exception{
		
		crawl("http://www.ics.uci.edu"); // call the method, input initial seed, the program running
		
	}
	
	
	/**
	 * This method is for testing purposes only. It does not need to be used
	 * to answer any of the questions in the assignment. However, it must
	 * function as specified so that your crawler can be verified programatically.
	 * 
	 * This methods performs a crawl starting at the specified seed URL. Returns a
	 * collection containing all URLs visited during the crawl.
	 * @throws Exception 
	 */

	public static Collection<String> crawl(String seedURL) throws Exception{
		String[] input = {seedURL};
		Controller.main(input);  // running the program
	    Collection<String> data = myCrawler.visitedUrls; // get the data from myCrawler
	    return data;
	}
}
