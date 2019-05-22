package com.souvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用servletContext实现请求转发
 *
 * @author souvc
 */
@WebServlet(name = "ServletContextDemo05", urlPatterns = {"/demo05"})
public class ServletContextDemo05 extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String data = "<h1><font color='red'>www.souvc.com</font></h1>";
        response.getOutputStream().write(data.getBytes());
        //获取ServletContext对象
        ServletContext context = this.getServletContext();
        //获取请求转发对象(RequestDispatcher)
        RequestDispatcher rd = context.getRequestDispatcher("/demo06");
        //调用forward方法实现请求转发
        rd.forward(request, response);

    }
}
