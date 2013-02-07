
package ir.assignments.two;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import edu.uci.ics.crawler4j.crawler.*;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class myCrawler extends WebCrawler {

    private final static Pattern FILTERS1 = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" 
                                                      + "|png|tiff?|mid|mp2|mp3|mp4"
                                                      + "|wav|avi|mov|mpeg|ram|m4v|pdf|doc|docx" 
                                                      + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    
    private final static Pattern FILTERS2 = Pattern.compile(".*(\\.ics\\.uci\\.edu).*");
    
	public static Collection<String> visitedUrls = new ArrayList<String>(); // store each url visited
	
	public static File u = new File("D:" + File.separator + "testUrls.txt"); // store every urls
	public static File t = new File("D:" + File.separator + "testText.txt"); // store every text
	
    /**
     * You should implement this function to specify whether
     * the given url should be crawled or not (based on your
     * crawling logic).
     */
    @Override
    public boolean shouldVisit(WebURL url) {
            String href = url.getURL().toLowerCase();
            return !FILTERS1.matcher(href).matches() && FILTERS2.matcher(href).matches() && !href.contains("ftp") && !href.contains("?") && !href.contains("calendar");
    } // filters: cannot contains filter1 and "?", must contains .ics\\.uci\\.edu, try other

    /**
     * This function is called when a page is fetched and ready 
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {          
            String url = page.getWebURL().getURL();
            System.out.println("URL: " + url);
            visitedUrls.add(url); // add the visited urls into ArrayList visitedUrls

            if (page.getParseData() instanceof HtmlParseData) {
                    HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                    String text = htmlParseData.getText(); // that page's all text
                    String html = htmlParseData.getHtml(); // that page's html address
                    List<WebURL> links = htmlParseData.getOutgoingUrls();  // store every links on this page in List

                    try {
                    	OutputStream outUrls = new FileOutputStream(u,true); // open file u: store urls
                    	OutputStream outText = new FileOutputStream(t,true); // open file t: store text
            		
                    	outUrls.write(url.getBytes()); //write data into file
                    	outUrls.write('\r'); // \r\n : newline
            			outUrls.write('\n'); 
            		
            			outText.write(text.getBytes()); // write data into file
            			outUrls.write('\r'); // \r\n : newline
            			outUrls.write('\n'); 
            		
            			outUrls.close(); // close the output
            			outText.close(); // close the output  
                    }
            		catch (IOException ex){
            			System.out.println("No file!");
            		}
                    
                    System.out.println("Text length: " + text.length());
                    System.out.println("Html length: " + html.length());
                    System.out.println("Number of outgoing links: " + links.size());
            	    System.out.println("Number of URLs which have been crawled: " + visitedUrls.size());
            	    
            }
    }
}
