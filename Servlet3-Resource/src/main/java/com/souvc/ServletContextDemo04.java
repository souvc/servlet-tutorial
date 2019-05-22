package com.souvc;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取WEB应用的初始化参数
 * 在web.xml文件中使用<context-param>标签配置WEB应用的初始化参数
 *
 * @author souvc
 */
@WebServlet(name = "ServletContextDemo04", urlPatterns = {"/demo04"})
public class ServletContextDemo04 extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        //获取整个web站点的初始化参数
        ServletContext context = this.getServletContext();
        String contextInitParam = context.getInitParameter("url");
        response.getWriter().print(contextInitParam);

    }

}
