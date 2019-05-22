package com.souvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 重定向
 */
public class PageRedirect2 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("http://www.souvc.com");

    }
} 