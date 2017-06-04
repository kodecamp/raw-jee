package com.kodecamp.webui.student;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class HtmlGeneratorServlet extends HttpServlet{

	private static final String HTML_START="<html>"+
											  "<head> "+
											     "<link rel=\"stylesheet\" type=\"text/css\" href=\"./index.css\">"+
											  "</head>"+
											  "<body>";
	private static final String HTML_END=     "</body>"+
											"</html>";
	private static int count = 0;
	private static int localCount = 0;

    
    /**
    *constructor
    */   
    public HtmlGeneratorServlet() {
        super();        
        this.count = this.count + 1;
        System.out.println("Constructor ==> : StartupServlet   ==> hello how are you");
    }


    /**
    *
    */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.localCount = this.localCount + 1;
		PrintWriter out = response.getWriter();
		
		String htmlPage = HTML_START + this.createHtmlFragment() + HTML_END;
		System.out.println("requestObject --> " + request.getSession());
		out.print(htmlPage);
		RequestDispatcher view = request.getRequestDispatcher("/views/home.jsp");
//		view.forward(request, response);
		view.include(request, response);
	}

	private String createHtmlFragment(){
		
		String dynamicHtmlFragment = "<h3>I am servlet ! Refresh the page and watch the changes carefully.</h3>"
			+ " <h3> " + this.count + " ( incremented only in constructor ) </h3>" 
			+ " <h3> " + this.localCount + " ( incremented only in doGet() ) </h3>";

		return dynamicHtmlFragment;
	}

}