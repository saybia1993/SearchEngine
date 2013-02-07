package TextProcessing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Count the total number of 2-grams and their frequencies in a text file.
 */
public final class TwoGramFrequencyCount {

	public static HashMap<String, Integer> allMap = new HashMap<String, Integer>();
	
	public static void main(String[] args) throws IOException {
		int bufSize = 10485760;  // 1024*1024*10 = 10485760 byte = 10MB, 14 times iteration
		byte[] bs = new byte[bufSize];
		ByteBuffer byteBuf = ByteBuffer.allocate(bufSize);
		FileChannel channel = new RandomAccessFile("D://AfterDeleteStop.txt","r").getChannel(); // input file
		int size;
		while((size = channel.read(byteBuf)) != -1) {  // each loop read 10MB text into memory
			byteBuf.rewind();
			byteBuf.get(bs);
			String s = new String(bs, 0, size);  // output : String s, this 10MB file
			StringTokenizer tokenizer = new StringTokenizer(s); // convert string to tokenizer's string
			String before = tokenizer.nextToken();
			String after = null;
			HashMap<String, Integer> map = new HashMap<String, Integer>(); // every loop's small HashMap
			while (tokenizer.hasMoreTokens()) {
				after = tokenizer.nextToken();
				String now = before + " " + after;
				if (map.containsKey(now)){
					map.put(now, map.get(now)+1);  // add this value into HashMap: map
				}
				else{
					map.put(now, 1);
				}
				before = after;
			} // now, every words in each circle has been added into map
			byteBuf.clear();
			// next sort:
			List<Frequency> list = new ArrayList<Frequency>(); // create a list for each map
			java.util.Iterator<String> iterator = map.keySet().iterator(); // iterate the map
			while (iterator.hasNext()){
				String key = iterator.next();
				int value = map.get(key);
				Frequency f = new Frequency(key, value);
				list.add(f); // put every string with their appearance times into list
			} 
			Frequency[] array = new Frequency[list.size()];
			list.toArray(array); // convert list to array
			
			// sort the array
			Frequency temp = null;
			int p =0;
			for (int i=0; i<array.length-1; i++){
				for (int j=i+1; j<array.length; j++){
					if (array[j].getFrequency()>array[i].getFrequency()){ // exchange two element
						temp = array[j];
						array[j] = array[i];
						array[i] = temp; // sort from large to small
					}
				}
				p++;
				System.out.println("Now: " + p + " / " + (array.length-1));
			}
			System.out.println("Each Loop:  array sorted !");
			
			for (int i=0; i<1000; i++){ // get 1000 most 2-grams from the array into allMap
				if (allMap.containsKey(array[i].getText())){
					int val = allMap.get(array[i].getText());
					allMap.put(array[i].getText(), val+array[i].getFrequency());
				}
				else {
					allMap.put(array[i].getText(), array[i].getFrequency());
				}
			}
			System.out.println("Added into allMap: " + allMap.size());
		} //while loop end
		channel.close(); // allMap contains all the dat now
		
		
		// sort allMap and output
		List<Frequency> allList = new ArrayList<Frequency>();
		java.util.Iterator<String> iterator = allMap.keySet().iterator(); // iterate the allMap
		while (iterator.hasNext()){
			String key = iterator.next();
			int value = allMap.get(key);
			Frequency f = new Frequency(key, value);
			allList.add(f); // put every string with their appearance times into allList
		}
		Frequency[] allArray = new Frequency[allList.size()];
		allList.toArray(allArray); // convert allList to allArray
		
		
		// sort the allArray
		Frequency temp = null;
		for (int i=0; i<allArray.length-1; i++){
			for (int j=i+1; j<allArray.length; j++){
				if (allArray[j].getFrequency()>allArray[i].getFrequency()){ // exchange two element
					temp = allArray[j];
					allArray[j] = allArray[i];
					allArray[i] = temp; // arrange from large to small
				}
			}
		}
		
		
		// output:
		File output = new File("D:" + File.separator + "TwoGramFrequencyCout.txt"); // create file
		String allString = "";
		for (int i=0; i<100; i++){ // get 100 most common 2-grams from allMap
			String frequency = allArray[i].getText() + ": " +allArray[i].getFrequency();
			allString = allString + "\r" + "\n" + frequency;
		}
		OutputStream out = new FileOutputStream(output,true); // open file u: store urls
		out.write("100 most common Two-grams:".getBytes());
		out.write(allString.getBytes()); //write data into file
		out.close(); // close the output
	}

}
