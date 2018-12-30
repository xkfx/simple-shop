package org.sample.shop.web.controller;

import org.sample.shop.dto.JsonError;
import org.sample.shop.entity.Item;
import org.sample.shop.service.ItemService;
import org.sample.shop.service.ServiceResult;
import org.sample.shop.service.impl.ItemServiceImpl;
import org.sample.shop.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ItemController extends HttpServlet {

    private ItemService itemService = ItemServiceImpl.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String param1 = req.getParameter("status");
        final String param2 = req.getParameter("uid");
        final String param3 = req.getParameter("curPage");
        final String param4 = req.getParameter("pageSize");
        final int status = Integer.parseInt(param1);
        final long uid = Long.parseLong(param2);
        final int curPage = Integer.parseInt(param3);
        final int pageSize = Integer.parseInt(param4);
        ServiceResult<List<Item>> result;
        if (status == 0) {
            result = itemService.listOffSale(uid, curPage, pageSize);
        } else {
            result = itemService.listOnSale(uid, curPage, pageSize);
        }

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
        super.doPost(req, resp);
    }
}
