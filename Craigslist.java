//imports of the net and pattern mattern for regex 
import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// class craigslist that takes in url and displays the content with no html
public class CraigsList {
  // main to run program 
	public static void main (String[] args) throws IOException {
		//declares string as a url
        String url;
        String craigslist = "";
        // url to open 
        URL address = new URL(args[0]);    	   
        // bufferreader to open url and stream its address
        InputStreamReader sr = new InputStreamReader(address.openStream());
        BufferedReader br = new BufferedReader(sr);

        // will go through each line of the url 
        while ((url = br.readLine()) != null)
        	craigslist += url + "\t";
        
        // matcher to match the regex pattern
        Matcher matcher; 
                
        // gets rid of title tag in html 
        Pattern Title = Pattern.compile("<title>(.*?)</title>", Pattern.DOTALL);
        //matcher to match the pattern of the regex
        matcher = Title.matcher(craigslist);
      //to find the matcher to replace html tags
        if (matcher.find()) {
        	String title = matcher.group(1);
        	//prints title in url 
        	System.out.println(title);
        	} 
        //pattern to find the body part of html code
        Pattern Body = Pattern.compile("<body class=\"posting\">(.*?)</body>", Pattern.DOTALL);
        // matches it to the pattern
        matcher = Body.matcher(craigslist);
      //to find the matcher to replace html tags
        if (matcher.find()) {
        	String body = matcher.group(1);
       //pattern to find the text inside the userbody in html code        
       Pattern regexbody = Pattern.compile("<div id=\"userbody\">(.*?)</div>", Pattern.DOTALL);             
       //matcher to match the body tags 
       matcher = regexbody.matcher(body);
       //gets rid of all the tags 
       String regex = "\\<.*?>"; 
       //pattern of regex 
       Pattern regexHtml = Pattern.compile(regex, Pattern.DOTALL);
                    
       //gets rid of the line breaks to clean it up 
       Pattern lineBreak = Pattern.compile("<br>");
       //to find the matcher to replace all lines that arent needed
       if (matcher.find()) {
    	   String description = matcher.group(1);
           String lineRegex = lineBreak.matcher(description).replaceAll("\n");
           String line = regexHtml.matcher(lineRegex).replaceAll("");
           System.out.println(line);
           } 
       // streamreader will close
       sr.close();
       // bufferreader will close
       br.close();
       } // ends if statement to find matcher  
        
	}// ends main 
	
}// ends class
