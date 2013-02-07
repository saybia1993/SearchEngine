package TextProcessing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.swing.text.html.HTMLDocument.Iterator;


/**
 * Counts the total number of words and their frequencies in a text file.
 */
public final class WordFrequencyCount {

	public static HashMap<String, Integer> map = new HashMap<String, Integer>();
	
	public static void main(String[] args) throws IOException {
		int bufSize = 1024;  // set buffer size to 1kb
		byte[] bs = new byte[bufSize];
		ByteBuffer byteBuf = ByteBuffer.allocate(bufSize);
		FileChannel channel = new RandomAccessFile("D://AfterDeleteStop.txt","r").getChannel(); // input file
		int size;
		while((size = channel.read(byteBuf)) != -1) {  // each loop read 1kb text into memory
			byteBuf.rewind();
			byteBuf.get(bs);
			String s = new String(bs, 0, size);  // output : String s, this 1kb file
			StringTokenizer tokenizer = new StringTokenizer(s); // convert string to tokenizer's string
			while (tokenizer.hasMoreTokens()) {
				String str = tokenizer.nextToken();
				if (map.containsKey(str)){
					map.put(str, map.get(str)+1);  // add this value into hashmap
				}
				else{
					map.put(str, 1);
				}
			}
			byteBuf.clear();
		}
		channel.close();
		
		List<Frequency> list = new ArrayList<Frequency>();
		
		java.util.Iterator<String> iterator = map.keySet().iterator(); // iterate the map
		
		while (iterator.hasNext()){
			String key = iterator.next();
			int value = map.get(key);
			Frequency f = new Frequency(key, value);
			list.add(f); // put every string with their appearance times into list for each 1kb file
		}

		Frequency[] array = new Frequency[list.size()];
		list.toArray(array); // convert list to array
		
		// arrange the list
		int p = 0;
		Frequency temp = null;
		for (int i=0; i<array.length-1; i++){
			for (int j=i+1; j<array.length; j++){
				if (array[j].getFrequency()>array[i].getFrequency()){ // exchange two element
					temp = array[j];
					array[j] = array[i];
					array[i] = temp; // arrange from small to large
				}
			}
			p++;
			System.out.println("ONE DONE:  " + p + "/"+ array.length);
		}
			
		File output = new File("D:" + File.separator + "WordFrequencyCout.txt"); // create file
		String allString = "";
		for (int i=0; i<1000; i++){
			String frequency = array[i].getText() + ": " +array[i].getFrequency();
			allString = allString + "\r" + "\n" + frequency;
		}
		OutputStream out = new FileOutputStream(output,true); // open file u: store urls
		out.write("1000 most common Words:".getBytes());
		out.write(allString.getBytes()); //write data into file
		out.close(); // close the output
	}
	
	
}
