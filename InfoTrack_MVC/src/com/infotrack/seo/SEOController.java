package com.infotrack.seo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.undo.StateEditable;

@WebServlet("/SEOController")
public class SEOController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SEOController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//user input from web page 
		String searchString = request.getParameter("search"); 
		String website = request.getParameter("website");
		
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		int count=0;
	    	StringBuilder statement = new StringBuilder();
	    	String url = "https://www.google.com.au/search?q="+searchString+"&num=100";
		System.out.println("URL to search on Browser ---------" + url);
		
	   //controller class calling the service methods
		 SEOService service = new SEOService();
	     	String webContent = service.readFromWeb(url);
	     	indexList = service.returnLinks(webContent,website);
	     	response.setContentType("text/html");
	     	PrintWriter out = response.getWriter();
	     
	     	if(!indexList.isEmpty())
	     	{
		     for (int index : indexList) {
					if(index  >= 0 && index <=100) {
						if(count == 0) {
							statement.append("The Company's Position on Search List is at " + index);
							count ++;
						}
						else {
							statement.append(", " + index);
							count ++;
						}
					}
					else {
						 out.println("<h2>");
				    	 out.println("Your Company is not listed in the first 100 search result");
				    	 out.println("</h2>");
					}
		     }
		     out.println("<h2>");
			out.println(statement);
	    	 out.println("</h2>");
		 }
	     else {
	    	 out.println("<h2>");
	    	 out.println("Your Company is not listed in the first 100 search result");
	    	 out.println("</h2>");
	     }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	}
