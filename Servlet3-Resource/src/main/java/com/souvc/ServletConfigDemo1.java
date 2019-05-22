package com.souvc;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 方式一：获取参数方式：配置Servlet初始化参数
 *
 * @author souvc
 */
public class ServletConfigDemo1 extends HttpServlet {

    private String message;

    /**
     * 定义ServletConfig对象来接收配置的初始化参数
     */
    private ServletConfig config;

    /**
     * 当servlet配置了初始化参数后，web容器在创建servlet实例对象时，
     * 会自动将这些初始化参数封装到ServletConfig对象中，并在调用servlet的init方法时，
     * 将ServletConfig对象传递给servlet。进而，程序员通过ServletConfig对象就可以
     * 得到当前servlet的初始化参数信息。
     */
    @Override
    public void init(ServletConfig config) {

        // 执行必需的初始化
        message = "Hello World";

        //初始化，方便其他方法进行调用
        this.config = config;

        //获取指定参数
        String name = config.getInitParameter("name");
        String password = config.getInitParameter("password");
        String charset = config.getInitParameter("charset");
        System.out.println("name:" + name);
        System.out.println("password" + password);
        System.out.println("charset:" + charset);

        //获取所有的初始化参数
        Enumeration<String> e = config.getInitParameterNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            String value = config.getInitParameter(key);
            System.out.println(key);
            System.out.println(value);
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        //设置编码
        response.setCharacterEncoding("UTF-8");

        // 设置响应内容类型
        response.setContentType("text/html;charset=utf-8");

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
        out.println("<h1>name:" + config.getInitParameter("name") + "</h1>");
        out.println("<h1>password:" + config.getInitParameter("password") + "</h1>");
        out.println("<h1>charset:" + config.getInitParameter("charset") + "</h1>");
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void destroy() {
        // 什么也不做
    }

}
