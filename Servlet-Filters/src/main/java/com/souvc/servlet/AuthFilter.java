package com.souvc.servlet;

import javax.servlet.*;

/**
 * @author souvc
 */
public class AuthFilter implements Filter {

    public void init(FilterConfig config) {

        // 获取初始化参数
        String site = config.getInitParameter("Site");

        // 输出初始化参数
        System.out.println("网站名称: " + site);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, ServletException {

        // 输出站点名称
        System.out.println("站点网址：http://www.souvc.com");

        System.out.println("ServerName:" + request.getServerName());

        // 把请求传回过滤链
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        /* 在 Filter 实例被 Web 容器从服务移除之前调用 */
        System.out.println("destroy()");
    }


}