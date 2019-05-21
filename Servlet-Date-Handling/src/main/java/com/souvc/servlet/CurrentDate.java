package com.souvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取当前的日期和时间
 */
public class CurrentDate extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
	public CurrentDate() {
		super();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
  		String title = "显示当前的日期和时间";
		Date date = new Date();
		String docType = "<!DOCTYPE html> \n";
		  out.println(docType +
		    "<html>\n" +
			"<head><title>" + title + "</title></head>\n" +
			"<body bgcolor=\"#f0f0f0\">\n" +
			"<h1 align=\"center\">" + title + "</h1>\n" +
			"<h2 align=\"center\">" + date.toString() + "</h2>\n" +
			"</body></html>");
	}

}