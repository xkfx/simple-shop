package org.sample.shop.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.dto.ServiceResult;
import org.sample.shop.service.SimpleUserService;
import org.sample.shop.service.impl.SimpleUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleUserController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private SimpleUserService userService = SimpleUserServiceImpl.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 拿参数
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");
        final String type = req.getParameter("type");
        // 调服务
        LOGGER.debug("SimpleUserController: username={}, password={}, type={}", username, password, type);
        ServiceResult result = userService.register(Integer.valueOf(type), username, password);
        LOGGER.debug("SimpleUserController: {}", result);
        // 返回json
        PrintWriter pw = resp.getWriter();
        String jsonString;
        if (result.isSuccess()) { // 成功返回数据
            resp.setStatus(201);
            jsonString = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(result.getData());
        } else { // 失败返回理由

            jsonString = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        }
        pw.print(jsonString);
        pw.flush();
    }
}
