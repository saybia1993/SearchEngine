package TextProcessing;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.StringTokenizer;

public class StopWords {

	public static File output = new File("D:" + File.separator + "AfterDeleteStop.txt"); // store the tokenizer file
	
		public static void main(String[] args) throws IOException {
			int bufSize = 1024;  // set buffer size to 1kb
			byte[] bs = new byte[bufSize];
			ByteBuffer byteBuf = ByteBuffer.allocate(bufSize);
			FileChannel channel = new RandomAccessFile("D://AfterTokenizer.txt","r").getChannel(); // input file
			int size;
			while((size = channel.read(byteBuf)) != -1) {  // each loop read 1kb text into memory
				byteBuf.rewind();
				byteBuf.get(bs);
				String s = new String(bs, 0, size);  // output : String s, this 1kb file
				deleteStop(s); // call method
				byteBuf.clear();
			}
			channel.close();
		}
		
		public static void deleteStop (String input) throws IOException{
			StringTokenizer tokenizer = new StringTokenizer(input); // convert string to tokenizer's string
			String sr = "";
			while (tokenizer.hasMoreTokens()) {
				String s = tokenizer.nextToken();
				if (s.equals("a")|s.equals("about")|s.equals("above")|s.equals("after")|s.equals("again")|s.equals("against")|s.equals("all")|s.equals("am")|s.equals("an")
					|s.equals("and")|s.equals("any")|s.equals("are")|s.equals("arent")|s.equals("as")|s.equals("at")|s.equals("be")|s.equals("because")|s.equals("by")
					|s.equals("been")|s.equals("before")|s.equals("being")|s.equals("below")|s.equals("between")|s.equals("both")|s.equals("but")|s.equals("cant")
					|s.equals("cannot")|s.equals("could")|s.equals("couldnt")|s.equals("did")|s.equals("didnt")|s.equals("do")|s.equals("does")|s.equals("doesnt")|s.equals("doing")|s.equals("dont")
					|s.equals("down")|s.equals("during")|s.equals("each")|s.equals("few")|s.equals("for")|s.equals("from")|s.equals("further")|s.equals("had")|s.equals("hadnt")|s.equals("has")
					|s.equals("hasnt")|s.equals("have")|s.equals("havent")|s.equals("having")|s.equals("he")|s.equals("hed")|s.equals("hell")|s.equals("hes")|s.equals("her")|s.equals("here")
					|s.equals("heres")|s.equals("hers")|s.equals("herself")|s.equals("him")|s.equals("himself")|s.equals("his")|s.equals("how")|s.equals("hows")|s.equals("i")|s.equals("id")
					|s.equals("ill")|s.equals("im")|s.equals("ive")|s.equals("if")|s.equals("in")|s.equals("into")|s.equals("is")|s.equals("isnt")|s.equals("it")|s.equals("its")|s.equals("itself")|s.equals("lets")
					|s.equals("me")|s.equals("more")|s.equals("mustnt")|s.equals("my")|s.equals("myself")|s.equals("no")|s.equals("nor")|s.equals("not")|s.equals("of")|s.equals("off")|s.equals("on")|s.equals("once")
					|s.equals("only")|s.equals("or")|s.equals("other")|s.equals("ought")|s.equals("our")|s.equals("ours")|s.equals("ourselves")|s.equals("out")|s.equals("over")|s.equals("own")|s.equals("same")
					|s.equals("shant")|s.equals("she")|s.equals("shed")|s.equals("shell")|s.equals("shes")|s.equals("should")|s.equals("shouldnt")|s.equals("so")|s.equals("some")|s.equals("such")|s.equals("than")
					|s.equals("that")|s.equals("thats")|s.equals("the")|s.equals("their")|s.equals("theirs")|s.equals("them")|s.equals("themselves")|s.equals("then")|s.equals("there")|s.equals("theres")|s.equals("these")
					|s.equals("they")|s.equals("theyd")|s.equals("theyll")|s.equals("theyre")|s.equals("theyve")|s.equals("this")|s.equals("those")|s.equals("through")|s.equals("to")|s.equals("too")|s.equals("under")
					|s.equals("until")|s.equals("up")|s.equals("very")|s.equals("was")|s.equals("wasnt")|s.equals("we")|s.equals("wed")|s.equals("well")|s.equals("were")|s.equals("weve")|s.equals("werent")
					|s.equals("what")|s.equals("whats")|s.equals("when")|s.equals("whens")|s.equals("where")|s.equals("wheres")|s.equals("which")|s.equals("while")|s.equals("who")|s.equals("whos")|s.equals("whom")
					|s.equals("why")|s.equals("whys")|s.equals("with")|s.equals("wont")|s.equals("would")|s.equals("wouldnt")|s.equals("you")|s.equals("youd")|s.equals("youll")|s.equals("youre")|s.equals("youve")
					|s.equals("your")|s.equals("yours")|s.equals("yourself")|s.equals("yourselves"))
					{
					s = ""; // replace stop words
				}
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

