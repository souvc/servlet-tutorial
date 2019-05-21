package com.souvc.servlet;
// 导入必需的 java 库
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/showError")
// 扩展 HttpServlet 类
public class ShowError extends HttpServlet {
 
	private static final long serialVersionUID = 1L;

	// 处理 GET 方法请求的方法
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
      // 设置错误代码和原因
      response.sendError(407, "Need authentication!!!" );
    }
    
    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
    	doGet(request, response);
    }
    
}