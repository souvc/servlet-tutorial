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
 * 多文件上传
 *
 * @author souvc
 */
@MultipartConfig(location = "d:/test")
@WebServlet("/muServlet3Upload")
public class MuServlet3Upload extends HttpServlet {

    /**
     * 文件上传处理逻辑
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //迭代Collection中所有的Part对象
        for (Part part : req.getParts()) {
            if (part.getName().startsWith("file")) {
                String filename = getFilename(part);
                part.write(filename);
            }
        }

        System.out.println("上传成功！！");

        PrintWriter out = resp.getWriter();
        out.println("<h1>上传成功</h1>");
    }

    /**
     * 获取文件名
     * @param part
     * @return
     */
    private String getFilename(Part part) {
        String header = part.getHeader("Content-Disposition");
        String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
        return filename;
    }
}