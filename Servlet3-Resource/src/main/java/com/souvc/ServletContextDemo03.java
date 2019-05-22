package com.souvc;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 从共享数据中获取
 * 先运行ServletContextDemo1，将数据data存储到ServletContext对象中，
 * <p>
 * 然后运行ServletContextDemo2就可以从ServletContext对象中取出数据了，这样就实现了数据共享
 *
 * @author souvc
 */
@WebServlet(name = "ServletContextDemo03", urlPatterns = {"/demo03"})
public class ServletContextDemo03 extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        ServletContext context = this.getServletContext();
        //从ServletContext对象中取出数据
        String commonData = (String) context.getAttribute("commonData");
        //将data存储到ServletContext对象中
        context.setAttribute("commonData", commonData);
        response.getWriter().print("commonData=" + commonData);
    }

}
