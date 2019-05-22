package com.souvc;

import com.souvc.demo.ClassLoaderTest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 1、 ServletContext   String getRealPath(java.lang.String path) 获取绝对路径，path 从项目开始/WEB-INF/cfg.properties
 * <p>
 * 2、URL  String getPath()获取URL对象的绝对路径。
 * <p>
 * 3、ClassLoader   URL getResource(String name) name 是从WEB-INF/classes开始的。例如：com/souvc/servlet/cfg.properties返回URL对象
 * <p>
 * 4、ResourceBundle   static final ResourceBundle getBundle(String baseName) baseName 的文件类型必须是.properties baseName不用写后缀名
 * 路径从WEB-INF/classes 开始， 文件夹/文件夹  表示方式为 文件夹.文件夹
 *
 * @author souvc
 */
@WebServlet(name = "ServletContextDemo08", urlPatterns = {"/demo08"})
public class ServletContextDemo08 extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //目的是控制浏览器用UTF-8进行解码
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");

        response.getWriter().println("<hr/>");
        test1(response);

        response.getWriter().println("<hr/>");
        test2(response);

        response.getWriter().println("<hr/>");
        test3(response);

        response.getWriter().println("<hr/>");
        test4(response);

        response.getWriter().println("<hr/>");
        test5(response);

        response.getWriter().println("<hr/>");
        test6(response);

    }

    /**
     * baseName 的文件类型必须是.properties baseName不用写后缀名路径从WEB-INF/classes 开始
     *
     * @param response
     */
    private void test6(HttpServletResponse response) throws IOException {
        response.getWriter().println("方式六、使用ResourceBundle 来读取 ");
        ResourceBundle rb = ResourceBundle.getBundle("db1", Locale.getDefault());
        String driver = rb.getString("driver");
        String url = rb.getString("url");
        String username = rb.getString("username");
        String password = rb.getString("password");
        String format = MessageFormat.format("driver={0},url={1},username={2},password={3}", driver, url, username, password);
        response.getWriter().print(format);
    }

    /**
     * 根目录是class文件所在目录,如果以 /开头从classpath目录中找db.properties;如果不以/开头从当前类所在的包中找
     *
     * @param response
     */
    private void test5(HttpServletResponse response) throws IOException {
        InputStream in = ServletContextDemo08.class.getClassLoader().getResourceAsStream("com/souvc/db4.properties");
        response.getWriter().println("方式五、使用类加载器去读取配置文件");
        String format = getString(in);
        response.getWriter().print(format);
    }


    /**
     * 读取大文件,并拷贝到e:\根目录下
     * 文件太大，只能用servletContext去读取
     *
     * @param response
     */
    private void test4(HttpServletResponse response) throws IOException {
        String path = this.getServletContext().getRealPath("/WEB-INF/classes/db1.properties");
        /**
         * path.lastIndexOf("\\") + 1是一个非常绝妙的写法
         */
        //获取文件名
        String filename = path.substring(path.lastIndexOf("\\") + 1);
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db1.properties");
        byte[] buffer = new byte[1024];
        int len = 0;
        OutputStream out = new FileOutputStream("e:\\" + filename);
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        out.close();
        in.close();
        response.getWriter().println("方式四、读取大文件,并拷贝到e:\\根目录下");
    }


    /**
     * 通过类装载器读取资源文件的注意事项:不适合装载大文件，否则会导致jvm内存溢出
     *
     * @param response
     */
    private void test3(HttpServletResponse response) throws IOException {

        /**
         * 使用类加载器去读取这个大文件时会导致内存溢出：
         * java.lang.OutOfMemoryError: Java heap space
         */
        InputStream in = ServletContextDemo08.class.getClassLoader().getResourceAsStream("apache-tomcat-7.0.81.zip");
        response.getWriter().println("方式三、使用类加载器去读取这个大文件时会导致内存溢出");
    }

    /**
     * 读取类路径下面、包下面的资源文件
     *
     * @param response
     * @throws IOException
     */
    private void test2(HttpServletResponse response) throws IOException {
        ClassLoader loader = ServletContextDemo08.class.getClassLoader();
        InputStream in = loader.getResourceAsStream("com/souvc/db4.properties");
        response.getWriter().println("方式二、读取类路径下面、包下面的资源文件：");
        String format = getString(in);
        response.getWriter().print(format);
    }

    /**
     * 读取类路径下的资源文件
     *
     * @param response
     */
    private void test1(HttpServletResponse response) throws IOException {
        ClassLoader loader = ServletContextDemo08.class.getClassLoader();
        InputStream in = loader.getResourceAsStream("db1.properties");
        response.getWriter().println("方式一、读取类路径下的资源文件：");
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
