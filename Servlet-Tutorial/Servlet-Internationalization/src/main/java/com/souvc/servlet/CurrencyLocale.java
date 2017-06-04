package com.souvc.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 特定于区域设置的货币
 * 您可以使用 java.text.NumberFormat 类
 * 其静态方法 getCurrencyInstance() 来格式化数字（比如 long 类型或 double 类型）为特定于区域设置的货币。
 */
public class CurrencyLocale extends HttpServlet{
    
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
		
    // 设置响应内容类型
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    // 获取客户端的区域设置
    Locale locale = request.getLocale( );
    NumberFormat nft = NumberFormat.getCurrencyInstance(locale);
    String formattedCurr = nft.format(1000000);

    String title = "特定于区域设置的货币";
    String docType = "<!DOCTYPE html> \n";
      out.println(docType +
      "<html>\n" +
      "<head><title>" + title + "</title></head>\n" +
      "<body bgcolor=\"#f0f0f0\">\n" +
      "<h1 align=\"center\">" + formattedCurr + "</h1>\n" +
      "</body></html>");
  }
} 