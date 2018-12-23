package org.sample.shop.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.service.ServiceResult;
import org.sample.shop.service.UserService;
import org.sample.shop.service.impl.UserServiceImpl;
import org.sample.shop.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.INSTANCE;

    /**
     * 用户登陆
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 拿参数
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");

        // 调服务
        LOGGER.debug("[SessionController]login: username={}, password={}", username, password);
        ServiceResult result = userService.login(username, password);
        LOGGER.debug("[SessionController]login: username={}, {}", username, result);

        // 返回HTTP状态码 + json
        PrintWriter pw = resp.getWriter();
        pw.print(JsonUtil.restfulResponse(result, resp));
        pw.flush();
    }
}
