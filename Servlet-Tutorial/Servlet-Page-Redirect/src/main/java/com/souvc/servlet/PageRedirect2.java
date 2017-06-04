package com.souvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageRedirect2 extends HttpServlet{
 
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
      // 设置响应内容类型
      response.setContentType("text/html;charset=UTF-8");
      response.sendRedirect("http://www.souvc.com");
      
    }
} 