package com.kodecamp.webui.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

	private final String dbUserName = "admin";
	private final String dbPassword = "admin";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("AuthenticationFilter : Init...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse)response;
		// Set standard HTTP/1.1 no-cache headers.
		resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");

		// Set standard HTTP/1.0 no-cache header.
		resp.setHeader("Pragma", "no-cache");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);
		System.out.println("AuthenticationFilter - session : " + session);
		if (session == null) {
			request.getRequestDispatcher("/").forward(httpRequest, response);
		} else {
			String userName = request.getParameter("userName");
			
			userName = userName == null ? (String) session.getAttribute("userName") : userName;

			String password = request.getParameter("password");
			password = password == null ? (String) session.getAttribute("userName") : password;
			System.out.println("Authenticator : Username : " + userName + " Password : " + password);

			if (this.dbUserName.equals(userName) && this.dbPassword.equals(password)) {
				session.setAttribute("userName", userName);
				session.setAttribute("password", password);
				session.setMaxInactiveInterval(120);
				
				chain.doFilter(httpRequest, response);
				
			}else{
				request.getRequestDispatcher("/").forward(httpRequest, response);
			}
		}

	}

	@Override
	public void destroy() {

	}

}
