package com.souvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageRedirect extends HttpServlet{
 
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
      // 设置响应内容类型
      response.setContentType("text/html;charset=UTF-8");

      // 要重定向的新位置
      String site = new String("http://www.souvc.com");

      response.setStatus(response.SC_MOVED_TEMPORARILY);
      response.setHeader("Location", site);    
    }
} 