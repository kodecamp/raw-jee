package com.kodecamp.webui.student;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class HttpFundamentalServlet extends HttpServlet{
    
    /**
    *constructor
    */   
    public HttpFundamentalServlet() {
        super();        
        System.out.println("HttpFundamentalServlet --> created");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		
//		out.println("-------------------- Http Request ----------------------");
//		out.println();
//		
//		out.println("requested resource path : " +  request.getContextPath() + request.getServletPath());
//		
//		Enumeration<String> requestHeaderNames = request.getHeaderNames();
//		
//		while(requestHeaderNames.hasMoreElements()) {
//		  String headerName = requestHeaderNames.nextElement();
//		  out.println(headerName + " : " + request.getHeader(headerName));
//		  out.println("");
//		}
//		
//		Enumeration<String> paramNames = request.getParameterNames();
//		
//		out.println("----------------- request paramaters -----------------");
//		while(paramNames.hasMoreElements()){
//			String paramName = paramNames.nextElement();
//			for(String value : request.getParameterValues(paramName)){
//				out.println("param name : " + paramName + " = " + value);
//			}
//		}
//		
//		out.println("-------------------- response --------------------------");
//		out.println("http status : " + response.getStatus());
//		out.println("content type : " + response.getContentType());
		
		RequestDispatcher view = request.getRequestDispatcher("/views/home.jsp");
		view.include(request, response);
		
		
	}

	

}