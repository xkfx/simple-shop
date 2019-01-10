package org.sample.shop.web.controller.v1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.dto.JsonError;
import org.sample.shop.entity.User;
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

/**
 * 封装成RESTful接口
 */
public class UserController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService = new UserServiceImpl();

    /**
     * 用户注册
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 拿参数
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");
        final String type = req.getParameter("type");
        // 调服务
        LOGGER.debug("[UserController]register: username={}, password={}, type={}", username, password, type);
        ServiceResult<User> result = userService.register(Integer.parseInt(type), username, password);
        LOGGER.debug("[UserController]register: username={}, {}", username, result);
        // 返回HTTP状态码 + json
        PrintWriter pw = resp.getWriter();
        if (result.isSuccess()) {
            resp.setStatus(200);
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result.getData()));
        } else {
            resp.setStatus(400);
            JsonError error = new JsonError(result.getDefaultDescription());
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(error));
        }
        pw.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 拿参数
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");
        // 调服务
        ServiceResult<User> result = userService.getUser(username, password);
        // 返回HTTP状态码 + json
        PrintWriter pw = resp.getWriter();
        if (result.isSuccess()) {
            resp.setStatus(200);
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result.getData()));
        } else {
            resp.setStatus(404);
            JsonError error = new JsonError(result.getDefaultDescription());
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(error));
        }
        pw.flush();
    }
}
