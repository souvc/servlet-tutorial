package com.souvc;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 实现文件上传
 *
 * @author souvc
 */
//@WebServlet("/servlet/Servlet3Upload")
@WebServlet(name = "Servlet3Upload", urlPatterns = {"/servlet3Upload"})
@MultipartConfig(location = "d:/test")
public class Servlet3Upload extends HttpServlet {

    /**
     * 处理上传文件
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //为了处理中文文件名
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //使用getPart()获得Part对象
        Part part = req.getPart("photo");
        String filename = getFilename(part);
        part.write(filename);
        System.out.println("上传成功！！");

        PrintWriter out = resp.getWriter();
        out.println("<h1>上传成功</h1>");
    }

    /**
     * 获取文件名
     *
     * @param part
     * @return
     */
    private String getFilename(Part part) {
        String header = part.getHeader("Content-Disposition");
        String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
        return filename;
    }


}
