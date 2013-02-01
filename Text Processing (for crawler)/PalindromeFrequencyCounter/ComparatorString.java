package ir.assignments.one.d;

import ir.assignments.one.a.Frequency;
import java.util.Comparator;

public class ComparatorString implements Comparator<Frequency>{ //Use comparator class to get the decreasing order based on frequency. 

	public int compare(Frequency f0, Frequency f1) {
		if(f0.getFrequency()>f1.getFrequency())
			return -1;
		else if(f0.getFrequency()<f1.getFrequency())
			return 1;
		else 
			return 0; 
	}  
	
}
 