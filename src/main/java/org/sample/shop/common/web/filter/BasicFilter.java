package org.sample.shop.common.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理中文乱码问题&允许跨域访问、各种HTTP方法
 */
public class BasicFilter implements Filter {

    private static String charset = "UTF-8";

    @Override
    public void init(FilterConfig config) throws ServletException {
        charset = config.getInitParameter("charset");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding(charset); // 不加的话req.getParameter获取中文会乱码
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        resp.setContentType("application/json");
        resp.setCharacterEncoding(charset);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}