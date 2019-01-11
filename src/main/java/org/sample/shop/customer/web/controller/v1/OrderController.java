package org.sample.shop.customer.web.controller.v1;

import org.sample.shop.common.dto.JsonError;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.customer.service.OrderService;
import org.sample.shop.customer.service.impl.OrderServiceImpl;
import org.sample.shop.common.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderController extends HttpServlet {

    private OrderService service = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String p1 = req.getParameter("uid");
        final String p2 = req.getParameter("pre");

        ServiceResult result;
        Long uid = Long.parseLong(p1);
        if (p2 != null) {
            result = service.getPreOrder(uid);
        } else {
            result = service.listOrders(uid);
        }

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
        final String p2 = req.getParameter("pay");
        final String p3 = req.getParameter("orderId");
        Long uid = Long.parseLong(p1);

        ServiceResult result;
        if (p2 != null && p3 != null) {
            Long orderId = Long.parseLong(p3);
            result = service.payOrder(orderId);
        } else {
            result = service.placeOrder(uid);
        }

        PrintWriter pw = resp.getWriter();
        if(result.isSuccess()) {
            resp.setStatus(201);
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result.getData()));
        } else {
            resp.setStatus(400);
            JsonError error = new JsonError(result.getDefaultDescription());
            pw.print(JsonUtil.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(error));
        }
        pw.flush();
    }
}
