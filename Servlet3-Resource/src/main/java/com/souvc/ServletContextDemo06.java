package com.souvc;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用servletContext实现请求转发
 * 访问的是ServletContextDemo5，浏览器显示的却是ServletContextDemo6的内容，这就是使用ServletContext实现了请求转发
 * @author souvc
 */
@WebServlet(name = "ServletContextDemo06", urlPatterns = {"/demo06"})
public class ServletContextDemo06 extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getOutputStream().write("ServletContextDemo6".getBytes());

    }
}
