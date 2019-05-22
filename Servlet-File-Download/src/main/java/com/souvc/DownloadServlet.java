package com.souvc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * 下载文件
 *
 * @author souvc
 */
//@WebServlet("/servlet/downloadServlet")
public class DownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String contentType = "application/x-msdownload";

    private String enc = "utf-8";

    public DownloadServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String filePath = request.getParameter("filePath");

        File file = new File( filePath);
        /* 如果文件存在 */
        if (file.exists()) {
            String fileName = URLEncoder.encode(file.getName(), enc);
            response.reset();

            //response.setContentType("application/vnd.ms-excel");
            //response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");//附件下载
            //response.addHeader("Content-Disposition", "inline; filename=a.xlsx");//在线打开
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
            /* 如果文件长度大于0 */
            if (fileLength > 0) {
                /* 创建输入流 */
                InputStream inStream = null;
                ServletOutputStream outStream = null;
                try {
                    inStream = new FileInputStream(file);
                    byte[] buf = new byte[4096];
                    /* 创建输出流 */
                    outStream = response.getOutputStream();
                    int readLength;
                    while (((readLength = inStream.read(buf)) != -1)) {
                        outStream.write(buf, 0, readLength);
                    }
                } finally {
                    inStream.close();
                    outStream.flush();
                    outStream.close();
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }

}
