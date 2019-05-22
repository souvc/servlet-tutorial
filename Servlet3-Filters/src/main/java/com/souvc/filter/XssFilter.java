package com.souvc.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * @author souvc
 */
@WebFilter("/*")
public class XssFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(XssFilter.class);

    @Override
    public void init(FilterConfig fConfig) {
        logger.info("初始化XssFilter#init");
    }

    public XssFilter() {
        XssSecurityManager.init(this.getClass().getResource("/").getPath() + "xss-config.xml");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        logger.info("经过XssFilter#doFilter");

        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {

    }


}
