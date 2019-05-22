package com.souvc;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet3HelloWorld
 *
 * @author souvc
 */
@WebServlet("Servlet3-HelloWorld")
public class Servlet3HelloWorld extends HttpServlet {

    private String message;

    @Override
    public void init() {
        // 执行必需的初始化
        message = "Hello World";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // 设置响应内容类型
        response.setContentType("text/html");
        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + ",welcome to Servlet world</h1>");
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void destroy() {
        // 什么也不做
    }

}
