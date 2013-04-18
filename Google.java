//imports of the net and pattern mattern for regex 
import java.io.*;
import java.net.*;
import java.util.regex.*;

//class Google that takes in url and displays the search results
public class Google {
  //main to run program
	public static void main(String[] args) {
		//google url to open
		Google url = new Google();
		//google search and user agent 
		String google = url.getLinks("http://www.google.com/search?q=%22gto%22&ie=UTF-8&oe=UTF-8&sourceid=ie7", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2)" +
				" Gecko/20100115 Firefox/3.6");
		//if google is not null than get the url 
		if (google != null)
			url.regex(google);
		}
	
	// Extract links from the given HTML string
	private void regex(String s) {
		//pattern to find html tags in google search
		Pattern p = Pattern.compile("<a href=(\".*?\"|[^>]*)>(.*?)</a>");
		//matcher to match the html tags
		Matcher m = p.matcher(s);
		//when finding regex will output the group
		while (m.find())
			System.out.println(m.group() + "\n\t" + m.group(1) + "\n\t" + m.group(2));
	}

	// Retrieve links from a given URL using the user agent
	private String getLinks(String url, String ua) {
		URL link;
		//try the link 
		try {
			link = new URL(url);
		} 
		//catch for any errors
		catch (MalformedURLException e) {
			System.err.println("ERROR: Malformed URL: " + url);
			return null;
		}
        //try the connection to the user agent and bufferreader
		try {
			String line;
			StringBuffer sb = new StringBuffer();
			URLConnection conn = link.openConnection();
			conn.setRequestProperty("User-agent", ua);
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			//while to get each line the reader goes through
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
				}
			return sb.toString();
			}
		//catch for any errors
		catch (IOException e) {
			System.err.println("ERROR: Unable to retrieve URL: " + url);
			e.printStackTrace();
			return null;
		} // end catch
	} //end getLinks method

}// end class
