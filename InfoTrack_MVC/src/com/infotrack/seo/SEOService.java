package com.infotrack.seo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SEOService {
	
	//This function will get the webpage content from the browser when the URL is hit
	public  String readFromWeb(String urlString) throws IOException {
		StringBuilder parsedContentFromUrl = new StringBuilder();
		try {
			URL url;
			if(urlString.contains(" "))
			    urlString = urlString.replace(" ", "%20");
			url = new URL(urlString);
			HttpURLConnection uc = (HttpURLConnection)url.openConnection();
			uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			uc.connect();
			uc.getInputStream();
			BufferedInputStream in = new BufferedInputStream(uc.getInputStream());
			int ch;
			while ((ch = in.read()) != -1) {
			    parsedContentFromUrl.append((char) ch);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parsedContentFromUrl.toString();
    }
  
	//This function will return only web site links ignoring all the other web content
		public ArrayList<Integer> returnLinks(String webContent,String website) {
				ArrayList<String> urlList  = new ArrayList<String>(); 
				ArrayList<Integer> indexList  = new ArrayList<Integer>(); 
				
				//this pattern will get all the  url's from the entire web page content
				Pattern pattern = Pattern.compile("a href=\"\\/url\\?q=https:\\/\\/(.*?)\\/");
				Matcher matcher = pattern.matcher(webContent);
				while (matcher.find())
				{	
						urlList.add(matcher.group(1));
					//	System.out.println(matcher.group(1));
				}
				
				int index = 0;
				//getting the index value from the list
				for (int i = 0; i < urlList.size(); i++) {
					if(urlList.get(i).contains(website.toLowerCase())) {
						index = i;
						indexList.add(index+1);
					}
				}
				return indexList;
	}
}
