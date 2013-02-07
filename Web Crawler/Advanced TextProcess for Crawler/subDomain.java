package TextProcessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class subDomain {
	
	public static void main(String[] args) {

		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("D://Urls.txt"));//open file
			String eachLine;
			eachLine = in.readLine();
			
			while(eachLine != null)
			{
				if(eachLine.length()>10)
				{
				String str = eachLine.substring(7, eachLine.indexOf("ics.uci.edu")+11);

				if (map.containsKey(str)) 

					map.put(str, map.get(str) + 1);

				else 
					map.put(str, 1);

					eachLine = in.readLine();
				}
				else
					eachLine = in.readLine();
			}
				System.out.println(map);
				  try {
					   String line = System.getProperty("line.separator");
					   StringBuffer str = new StringBuffer();
					   FileWriter fw = new FileWriter("D://subDomain.txt", true);
					   Set set = map.entrySet();
					   Iterator iter = set.iterator();
					   while(iter.hasNext()){
					    Map.Entry entry = (Map.Entry)iter.next(); 
					    str.append(entry.getKey()+" : "+entry.getValue()).append(line);
					   }
					   fw.write(str.toString());
					   fw.close();
					  } catch (IOException e) {
					   // TODO Auto-generated catch block
					   e.printStackTrace();
					  }
		}
		catch(IOException ex){
			System.out.println("File doesn't exist");
			}
	}
}
