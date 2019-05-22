package com.souvc.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试是否会进行拦截
 * 测试例子1：
 * <p>
 * http://localhost:8081/filter/demo?title=namealert(1)
 * <p>
 * 结果：title:
 * <p>
 * 测试例子2：
 *
 * <p>
 * http://localhost:8081/filter/demo?title=name
 * <p>
 * * 结果：title:name
 *
 * @author souvc
 */
@WebServlet(name = "ServletFilterDemo", urlPatterns = {"/filter/demo"})
public class ServletFilterDemo extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(ServletFilterDemo.class);

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        logger.info("title:{}", title);
        response.getOutputStream().write("ServletFilterDemo".getBytes());
    }
}
