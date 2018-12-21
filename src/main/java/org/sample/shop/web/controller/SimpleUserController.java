package org.sample.shop.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

public class SimpleUserController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final ObjectMapper MAPPER = JsonUtil.getMapper();
    private UserService userService = UserServiceImpl.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 拿参数
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");
        final String type = req.getParameter("type");
        // 调服务
        LOGGER.debug("[UserController]register: username={}, password={}, type={}", username, password, type);
        ServiceResult result = userService.register(Integer.valueOf(type), username, password);
        LOGGER.debug("[UserController]register: username={}, {}", username, result);
        // 返回HTTP状态码 + json
        PrintWriter pw = resp.getWriter();
        pw.print(JsonUtil.restfulResponse(resp, result));
        pw.flush();
    }
}
