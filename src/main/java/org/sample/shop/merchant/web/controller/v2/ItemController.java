package org.sample.shop.merchant.web.controller.v2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.common.dto.JsonError;
import org.sample.shop.common.entity.Item;
import org.sample.shop.merchant.service.ItemService;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.merchant.service.impl.ItemServiceImpl;
import org.sample.shop.common.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ItemController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 拿参数
        final String parameter = req.getParameter("uid");
        final String p2 = req.getParameter("status");
        final Long uid = Long.parseLong(parameter);

        // 根据参数签名调服务
        ServiceResult<List<Item>> result;
        if (p2 != null) {
            final int status = Integer.parseInt(p2);
            result = itemService.listByUidAndStatusNew(uid, status);
        } else {
            result = itemService.listByUidAndStatusNew(uid);
        }

        // 将结果解析封装成对应的接口
        PrintWriter pw = resp.getWriter();
        if(result.getCode() % 100 >= 50) { // 失败
            resp.setStatus(404);
            JsonError error = new JsonError(result.getDefaultDescription());
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(error));
        } else {
            resp.setStatus(200);
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result.getData()));
        }
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 拿参数
        final String p1 = req.getParameter("uid");
        final String name = req.getParameter("name");
        final String p3 = req.getParameter("price");
        final String p4 = req.getParameter("quantity");
        final Long uid = Long.parseLong(p1);
        final Double price = Double.parseDouble(p3);
        final int quantity = Integer.parseInt(p4);
        // 根据参数签名调服务
        ServiceResult<Item> result = itemService.createNew(uid, name, price, quantity);
        // 将结果解析封装成对应的接口
        PrintWriter pw = resp.getWriter();
        if(result.getCode() % 100 >= 50) { // 失败
            resp.setStatus(400);
            JsonError error = new JsonError(result.getDefaultDescription());
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(error));
        } else {
            resp.setStatus(201);
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result.getData()));
        }
        pw.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String p1 = req.getParameter("id");
        final Long id = Long.parseLong(p1);
        // 根据参数签名调服务
        ServiceResult<Item> result = itemService.delete(id);
        // 将结果解析封装成对应的接口
        PrintWriter pw = resp.getWriter();
        if(result.getCode() % 100 >= 50) { // 失败
            resp.setStatus(400);
            JsonError error = new JsonError(result.getDefaultDescription());
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(error));
        } else {
            resp.setStatus(204);
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result.getData()));
        }
        pw.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String p1 = req.getParameter("id");
        final String p2 = req.getParameter("status");
        final String name = req.getParameter("name");
        final String p3 = req.getParameter("price");
        final String p4 = req.getParameter("quantity");

        ServiceResult<Item> result;
        final Long id = Long.parseLong(p1);
        if (name != null && p3 != null && p4 != null) {
            final Double price = Double.parseDouble(p3);
            final int quantity = Integer.parseInt(p4);
            result = itemService.updateInfo(id, name, price, quantity);
        } else {
            final int status = Integer.parseInt(p2);
            result = itemService.updateStatus(id, status);
        }
        // 将结果解析封装成对应的接口
        PrintWriter pw = resp.getWriter();
        if(result.getCode() % 100 >= 50) { // 失败
            resp.setStatus(400);
            JsonError error = new JsonError(result.getDefaultDescription());
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(error));
        } else {
            resp.setStatus(200);
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result.getData()));
        }
        pw.flush();
    }
}
