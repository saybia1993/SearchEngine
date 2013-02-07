package TextProcessing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.StringTokenizer;

public class tokenizer {
	
	public static File output = new File("D:" + File.separator + "AfterTokenizer.txt"); // store the tokenizer file
	
	public static void main(String[] args) throws Exception {
		int bufSize = 1024;  // set buffer size to 1kb
		byte[] bs = new byte[bufSize];
		ByteBuffer byteBuf = ByteBuffer.allocate(bufSize);
		FileChannel channel = new RandomAccessFile("D://testText.txt","r").getChannel(); // input file
		int size;
		while((size = channel.read(byteBuf)) != -1) {  // each loop read 1kb text into memory
			byteBuf.rewind();
			byteBuf.get(bs);
			String s = new String(bs, 0, size);  // output : String s, this 1kb file
			tokenizeFile(s); // call method
			byteBuf.clear();
		}
		channel.close();
	}
	
	// method, convert String into token stream and add each token into a .txt file
	public static void tokenizeFile(String input) throws IOException {	
		StringTokenizer tokenizer = new StringTokenizer(input); // convert string to tokenizer's string
		String sr = " ";
		while (tokenizer.hasMoreTokens()) {
			String s = tokenizer.nextToken();
			s = s.replaceAll("[^A-Za-z]", "").toLowerCase(); // replace symbol, number
			sr = sr +" "+ s;
			sr = sr.replaceAll(" {2,}", " ");
		}
		OutputStream out = new FileOutputStream(output,true); // open file u: store urls
		out.write(sr.getBytes()); //write data into file
    	out.write('\r'); // \r\n : newline
		out.write('\n'); 
		out.close(); // close the output
	}
	
	
}
