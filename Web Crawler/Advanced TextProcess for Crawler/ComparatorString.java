package TextProcessing;

import java.util.Comparator;

public class ComparatorString implements Comparator<Frequency>{

 public int compare(Frequency f0, Frequency f1) {
  
  if(f0.getText().compareTo(f1.getText())>0)
	  return -1;
  else if(f0.getText().compareTo(f1.getText())<0)
	  return 1;
  else 
	  return 0; 
  }  
 }
 