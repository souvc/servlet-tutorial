package com.souvc;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * 使用servletContext读取资源文件
 *
 * @author souvc
 */
@WebServlet(name = "ServletContextDemo07", urlPatterns = {"/demo07"})
public class ServletContextDemo07 extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //目的是控制浏览器用UTF-8进行解码
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");

        response.getWriter().println("<hr/>");
        //方式一、读取src/main/目录下的properties配置文件
        readSrcDirPropCfgFile(response);

        response.getWriter().println("<hr/>");
        //方式二、读取Webapp目录下的properties配置文件
        readWebRootDirPropCfgFile(response);

        response.getWriter().println("<hr/>");
        //方式三、读取src/resources/db/config包中的db3.properties配置文件
        readPropCfgFile(response);

        response.getWriter().println("<hr/>");
        //方式四、读取src/java/com/souvc包中的db4.properties配置文件
        readPropCfgFile2(response);

        response.getWriter().println("<hr/>");
        //方式五、读取"/WEB-INF/包中的db5.properties配置文件
        readWebInfDirPropCfgFile(response);
    }

    /**
     * 方式五、读取"/WEB-INF/包中的db5.properties配置文件
     *
     * @param response
     */
    private void readWebInfDirPropCfgFile(HttpServletResponse response) throws IOException {
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/db5.properties");
        response.getWriter().println("方式五、读取/WEB-INF/包中的db5.properties配置文件：");
        String format = getString(in);
        response.getWriter().print(format);
    }

    /**
     * 方式四、读取src目录下的com.souvc包中的db4.properties配置文件
     *
     * @param response
     */
    private void readPropCfgFile2(HttpServletResponse response) throws IOException {
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/com/souvc/db4.properties");
        response.getWriter().println("方式四、读取src/java/com/souvc包中的db4.properties配置文件：");
        String format = getString(in);
        response.getWriter().print(format);
    }

    /**
     * 方式三、读取src目录下的db.config包中的db3.properties配置文件
     *
     * @param response
     */
    private void readPropCfgFile(HttpServletResponse response) throws IOException {
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db/config/db3.properties");
        response.getWriter().println("方式三、读取src目录下的db.config包中的db3.properties配置文件：");
        String format = getString(in);
        response.getWriter().print(format);
    }

    /**
     * 方式二、读取WebRoot目录下的properties配置文件
     *
     * @param response
     */
    private void readWebRootDirPropCfgFile(HttpServletResponse response) throws IOException {

        /**
         * 通过ServletContext对象读取Webapp或者WebRoot目录下的properties配置文件
         * "/"代表的是项目根目录
         */
        InputStream in = this.getServletContext().getResourceAsStream("/db2.properties");
        response.getWriter().println("方式二、读取Webapp或者WebRoot目录下的db2.properties配置文件：");
        String format = getString(in);
        response.getWriter().print(format);
    }

    /**
     * 方式一、读取src目录下的properties配置文件
     *
     * @param response
     */
    private void readSrcDirPropCfgFile(HttpServletResponse response) throws IOException {

        //通过ServletContext对象读取src目录下的db1.properties配置文件
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db1.properties");
        response.getWriter().println("方式一、读取src/main目录下的db1.properties配置文件：");
        String format = getString(in);
        response.getWriter().print(format);
    }


    /**
     * 获取配置信息
     *
     * @param in
     * @return
     * @throws IOException
     */
    private String getString(InputStream in) throws IOException {
        Properties prop = new Properties();
        prop.load(in);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        return MessageFormat.format("driver={0},url={1},username={2},password={3}", driver, url, username, password);
    }


}
