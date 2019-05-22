package com.souvc.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author souvc
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest orgRequest = null;

    /**
     * 判断是否是上传 上传忽略
     */
    boolean isUpData = false;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
        String contentType = request.getContentType();
        if (null != contentType) {
            isUpData = contentType.startsWith("multipart");
        }
    }

    @Override
    public String getQueryString() {
        return xssEncode(super.getQueryString());
    }

    /**
     * 获取request的属性时，做xss过滤
     */
    @Override
    public Object getAttribute(String name) {
        Object value = super.getAttribute(name);
        if (null != value && value instanceof String) {
            value = xssEncode((String) value);
        }
        return value;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (isUpData) {
            return super.getInputStream();
        } else {

            final ByteArrayInputStream bais = new ByteArrayInputStream(
                    inputHandlers(super.getInputStream()).getBytes("utf-8"));

            return new ServletInputStream() {

                @Override
                public int read() throws IOException {
                    return bais.read();
                }
            };
        }
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            int length = values.length;
            String[] escapseValues = new String[length];
            for (int i = 0; i < length; i++) {
                escapseValues[i] = xssEncode(values[i]);
            }
            return escapseValues;
        }
        return super.getParameterValues(name);
    }

    public String inputHandlers(ServletInputStream servletInputStream) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(servletInputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (servletInputStream != null) {
                try {
                    servletInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return xssEncode(sb.toString());
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter((name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    private static String xssEncode(String s) {
        if (XssSecurityConfig.REPLACE) {
            s = XssSecurityManager.securityReplace(s);
        }
        return s;
    }

    /**
     * 获取最原始的request
     *
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     *
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }

        return req;
    }

    public static void main(String[] args) {
        String reg = "^.*[A|a][L|l][E|e][R|r][T|t]\\s*\\(.*\\).*|.*[W|w][I|i][N|n][D|d][O|o][W|w]\\.[L|l][O|o][C|c][A|a][T|t][I|i][O|o][N|n]\\s*=.*|.*[S|s][T|t][Y|y][L|l][E|e]\\s*=.*[X|x]:[E|e][X|x].*[P|p][R|r][E|e][S|s]{1,2}[I|i][O|o][N|n]\\s*\\(.*\\).*|.*[D|d][O|o][C|c][U|u][M|m][E|e][N|n][T|t]\\.[C|c][O|o]{2}[K|k][I|i][E|e].*|.*[E|e][V|v][A|a][L|l]\\s*\\(.*\\).*|.*[U|u][N|n][E|e][S|s][C|c][A|a][P|p][E|e]\\s*\\(.*\\).*|.*[E|e][X|x][E|e][C|c][S|s][C|c][R|r][I|i][P|p][T|t]\\s*\\(.*\\).*|.*[M|m][S|s][G|g][B|b][O|o][X|x]\\s*\\(.*\\).*|.*[C|c][O|o][N|n][F|f][I|i][R|r][M|m]\\s*\\(.*\\).*|.*[P|p][R|r][O|o][M|m][P|p][T|t]\\s*\\(.*\\).*|.*<[S|s][C|c][R|r][I|i][P|p][T|t]>.*</[S|s][C|c][R|r][I|i][P|p][T|t]>.*|[[.&[^a]]|[|a|\\n|\\r\\n|\\r|\\u0085|\\u2028|\\u2029]]*<[S|s][C|c][R|r][I|i][P|p][T|t]>.*</[S|s][C|c][R|r][I|i][P|p][T|t]>[[.&[^a]]|[|a|\\n|\\r\\n|\\r|\\u0085|\\u2028|\\u2029]]*$";
        String s = "[{\"a\":\"<div>111</div>\"}]";
        System.out.println(s.replaceAll(reg, ""));
    }

}
