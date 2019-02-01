package org.sample.shop.customer.web.controller.v1;

import org.sample.shop.common.dto.JsonError;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.Cart;
import org.sample.shop.common.util.JsonUtil;
import org.sample.shop.customer.service.CartService;
import org.sample.shop.customer.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CartController extends HttpServlet {

    private CartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String p1 = req.getParameter("uid");
        Long uid = Long.parseLong(p1);

        ServiceResult<List<Cart>> result = cartService.listByUid(uid);

        PrintWriter pw = resp.getWriter();
        if(result.isSuccess()) {
            resp.setStatus(200);
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result.getData()));
        } else {
            resp.setStatus(404);
            JsonError error = new JsonError(result.getDefaultDescription());
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(error));
        }
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String p1 = req.getParameter("uid");
        final String p2 = req.getParameter("itemId");
        Long uid = Long.parseLong(p1);
        Long itemId = Long.parseLong(p2);

        ServiceResult<Cart> result = cartService.addItem(uid, itemId);

        PrintWriter pw = resp.getWriter();
        JsonError error = new JsonError(result.getDefaultDescription());
        if (result.isSuccess()) {
            resp.setStatus(201);
        } else {
            resp.setStatus(400);
        }
        pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(error));
        pw.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String p1 = req.getParameter("id");
        Long id = Long.parseLong(p1);

        ServiceResult<Cart> result = cartService.removeItem(id);

        PrintWriter pw = resp.getWriter();
        if(result.isSuccess()) {
            resp.setStatus(204);
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result.getData()));
        } else {
            resp.setStatus(400);
            JsonError error = new JsonError(result.getDefaultDescription());
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(error));
        }
        pw.flush();
    }
}
