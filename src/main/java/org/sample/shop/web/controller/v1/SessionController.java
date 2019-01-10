package org.sample.shop.web.controller.v1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.dto.ServiceResult;
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
    private final UserService userService = new UserServiceImpl();

    /**
     * 用户登陆
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 拿参数
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");

        // 调服务
        LOGGER.debug("[SessionController]getUser: username={}, password={}", username, password);
        ServiceResult result = userService.getUser(username, password);
        LOGGER.debug("[SessionController]getUser: username={}, {}", username, result);

        // 返回HTTP状态码 + json
        PrintWriter pw = resp.getWriter();
        pw.print(JsonUtil.restfulResponse(result, resp));
        pw.flush();
    }
}
