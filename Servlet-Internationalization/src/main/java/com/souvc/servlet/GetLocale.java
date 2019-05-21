package com.souvc.servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Locale;

/**
 * 如何显示某个请求的语言和相关的国家
 *
 */
public class GetLocale extends HttpServlet{
  
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
		
      // 获取客户端的区域设置
      Locale locale = request.getLocale();
      
      String language = locale.getLanguage();
      String country = locale.getCountry();

      // 设置响应内容类型
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      String title = "检测区域设置";
      String docType = "<!DOCTYPE html> \n";
      out.println(docType +
        "<html>\n" +
        "<head><title>" + title + "</title></head>\n" +
        "<body bgcolor=\"#f0f0f0\">\n" +
        "<h1 align=\"center\">" + language + "</h1>\n" +
        "<h2 align=\"center\">" + country + "</h2>\n" +
        "</body></html>");
  }
} 