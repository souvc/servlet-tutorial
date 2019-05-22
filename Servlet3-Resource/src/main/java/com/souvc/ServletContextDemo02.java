package com.souvc;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * WEB容器在启动时，它会为每个WEB应用程序都创建一个对应的ServletContext对象，它代表当前web应用。
 * ServletConfig对象中维护了ServletContext对象的引用，开发人员在编写servlet时，可以通过ServletConfig.getServletContext方法获得ServletContext对象。
 * 由于一个WEB应用中的所有Servlet共享同一个ServletContext对象，因此Servlet对象之间可以通过ServletContext对象来实现通讯。
 * ServletContext对象通常也被称之为context域对象。
 * <p>
 * 多个Servlet通过ServletContext对象实现数据共享
 * <p>
 * ServletContextDemo1和ServletContextDemo2通过ServletContext对象实现数据共享
 *
 * @author souvc
 */

@WebServlet(name = "ServletContextDemo02", urlPatterns = {"/demo02"})
public class ServletContextDemo02 extends HttpServlet {


    /**
     * 共享数据
     */
    String commonData = "commonData";


    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");


        /**
         * ServletConfig对象中维护了ServletContext对象的引用，开发人员在编写servlet时
         * 可以通过ServletConfig.getServletContext方法获得ServletContext对象。
         */
        ServletContext context = this.getServletConfig().getServletContext();

        //将data存储到ServletContext对象中
        context.setAttribute("commonData", commonData);
        response.getWriter().print("存储成功！");
    }

}
