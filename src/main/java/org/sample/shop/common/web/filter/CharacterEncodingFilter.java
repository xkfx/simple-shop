package org.sample.shop.common.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理中文乱码问题
 */
public class CharacterEncodingFilter implements Filter {

    private static String charset = "UTF-8"; // 默认utf-8

    @Override
    public void init(FilterConfig config) throws ServletException {
        charset = config.getInitParameter("charset");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req2 = (HttpServletRequest) req;
        req2.setCharacterEncoding(charset); // 不加的话req.getParameter获取中文会乱码

        // 允许跨域，暂时用于API测试
        HttpServletResponse resp2 = (HttpServletResponse) resp;
        resp2.setHeader("Access-Control-Allow-Credentials", "true");
        resp2.setHeader("Access-Control-Allow-Origin", req2.getHeader("Origin"));
        resp2.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        resp.setContentType("application/json");
        resp.setCharacterEncoding(charset);

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}