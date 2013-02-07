package ir.assignments.two;


import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


/**
 * You should also implement a controller class which specifies: 
 * 1. the seeds of the crawl
 * 2. the folder in which intermediate crawl data should be stored
 * 3. number of concurrent threads
 */

public class Controller {
    public static void main(String[] args) throws Exception {
            String crawlStorageFolder = "D://temp";   // the folder to store the intermediate crawl data
            int numberOfCrawlers = 8;  // concurrent threads

            CrawlConfig config = new CrawlConfig();
            config.setCrawlStorageFolder(crawlStorageFolder);

            /*
             * Instantiate the controller for this crawl. 
             */
            PageFetcher pageFetcher = new PageFetcher(config);
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

            /*
             * For each crawl, you need to add some seed urls. These are the first
             * URLs that are fetched and then the crawler starts following links
             * which are found in these pages
             */
            controller.addSeed(args[0]);    // the initial seed

            /*
             * set the user agent information
             */
            config.setUserAgentString("UCI IR crawler 28243494, 25085877");
            
            /*
             * Start the crawl. This is a blocking operation, meaning that your code
             * will reach the line after this only when crawling is finished.
             */
            controller.start(myCrawler.class, numberOfCrawlers);
                	    
    }
}
