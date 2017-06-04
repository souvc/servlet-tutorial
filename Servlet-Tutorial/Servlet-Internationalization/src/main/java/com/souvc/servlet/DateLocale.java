package com.souvc.servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Locale;
import java.text.DateFormat;
import java.util.Date;

/**
 * 特定于区域设置的日期
 * 您可以使用 java.text.DateFormat 类及其静态方法 getDateTimeInstance() 来格式化特定于区域设置的日期和时间。
 */
public class DateLocale extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
		
    // 设置响应内容类型
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    // 获取客户端的区域设置
    Locale locale = request.getLocale( );
    String date = DateFormat.getDateTimeInstance(
                                  DateFormat.FULL, 
                                  DateFormat.SHORT, 
                                  locale).format(new Date( ));

    String title = "特定于区域设置的日期";
    String docType = "<!DOCTYPE html> \n";
      out.println(docType +
      "<html>\n" +
      "<head><title>" + title + "</title></head>\n" +
      "<body bgcolor=\"#f0f0f0\">\n" +
      "<h1 align=\"center\">" + date + "</h1>\n" +
      "</body></html>");
  }
} 