//imports of the net and pattern mattern for regex 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//class Amazon that takes in url and displays the prouduct details 
public class Amazon {
  // main to run program 
	public static void main (String[] args) throws IOException {
		//declares string as a url
		String url;
		String amazon = "";
		// url that will open 
		URL address = new URL(args[0]);
		
		 // bufferreader to open url and stream its address
		InputStreamReader sr = new InputStreamReader(address.openStream());
		BufferedReader br = new BufferedReader(sr);
		
		// will go through each line of the url 
		while ((url = br.readLine()) != null) {
			amazon += url;
			}
		
		 // matcher to match the regex pattern
		Matcher matcher; 
		
		// gets rid of title tag in html 
		Pattern title = Pattern.compile("<title>(.*?)</title>");
		//to find the matcher to replace html tags
		matcher = title.matcher(amazon);
		if (matcher.find()) {
			String Title = matcher.group(1);
			// prints title in url
			System.out.println(Title);
			}
		
		// gets rid of html tags in reading level
		Pattern readinglevel = Pattern.compile("<li><b>Reading level:</b> (.*?)<br /></li>");
		//matcher to match the pattern of the regex
		matcher = readinglevel.matcher(amazon);
		if (matcher.find()) {
			String ReadingLevel = matcher.group(1);
			// prints reading level in url
			System.out.println("Reading Level: " + ReadingLevel);
			}
		// gets rid of html tags in library binding
		Pattern librarybinding = Pattern.compile("<li><b>Library Binding:</b> (.*?)</li>");
		// matcher to match the pattern of html
		matcher = librarybinding.matcher(amazon);
		//to find the matcher to replace all lines to extract the text
		if (matcher.find()) {
			String LibraryBinding = matcher.group(1);
			//prints library binding in url
			System.out.println("Library Binding: " + LibraryBinding);
			}
		// gets rid of html tags in publisher 
		Pattern publisher = Pattern.compile("<li><b>Publisher:</b> (.*?)</li>");
	    // matcher to match the pattern of html 
		matcher = publisher.matcher(amazon);
		// to find matcher to replace all lines to extract the text
		if (matcher.find()) {
			String Publisher = matcher.group(1);
			System.out.println("Publisher: " + Publisher);
			}
		//gets rid of html tags in langauage
		Pattern language = Pattern.compile("<li><b>Language:</b> (.*?)</li>");
		// matcher to match the pattern of html
		matcher = language.matcher(amazon);
		//to find matcher to replace all lines to extract the text
		if (matcher.find()) {
			String Language = matcher.group(1);
			System.out.println("Language: " + Language);
			}
		//gets rid of html tags in isbn10
		Pattern isbn10 = Pattern.compile("<li><b>ISBN-10:</b> (.*?)</li>");
		//matcher to match the pattern of html
		matcher = isbn10.matcher(amazon);
		//to find matcher to replace all lines to extract the text
		if (matcher.find()) {
			String Isbn10 = matcher.group(1);
			System.out.println("ISBN-10: " + Isbn10);
			}
		//gets rid of html tags in isbn13
		Pattern isbn13 = Pattern.compile("<li><b>ISBN-13:</b> (.*?)</li>");
		//matcher to match the pattern of html
		matcher = isbn13.matcher(amazon);
		//to find matcher to replace all lines to extract the text 
		if (matcher.find()) {
			String Isbn13 = matcher.group(1);
			System.out.println("ISBN-13: " + Isbn13);
			}
		//gets rid of html code in the image 
		Pattern picture = Pattern.compile("(?im)<img(.*?)src\\s*=\\s*[\"'](.*?)[\"']");
		//matcher to match the pattern of html
		matcher = picture.matcher(amazon);
		//to find matcher to replace all lines to extract the text
		if (matcher.find()) {
			String Picture = matcher.group(1);
			System.out.println("Image: " + Picture);
		}
	} //end main
} //end class
