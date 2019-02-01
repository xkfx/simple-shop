package org.sample.shop.merchant.web.controller.v1;

import org.sample.shop.common.dto.JsonError;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.enums.business.BusinessCode;
import org.sample.shop.common.util.JsonUtil;
import org.sample.shop.merchant.service.OrderDetailService;
import org.sample.shop.merchant.service.impl.OrderDetailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderDetailController extends HttpServlet {

    private OrderDetailService service = new OrderDetailServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String p1 = req.getParameter("merchantId");
        final String p2 = req.getParameter("oid");

        ServiceResult result = new ServiceResult(BusinessCode.INNER_ERROR);
        if (p1 != null) {
            Long merchantId = Long.parseLong(p1);
            result = service.getByMerchantId(merchantId);
        } else if (p2 != null){
            Long oid = Long.parseLong(p2);
            result = service.getByOrderId(oid);
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
        // 商家发货deliver/买家付款pay
        final String p1 = req.getParameter("detailId");
        final String p2 = req.getParameter("expressUid");
        final String p3 = req.getParameter("pay");

        ServiceResult result = new ServiceResult(BusinessCode.INNER_ERROR);
        Long detailId = Long.parseLong(p1);
        if (p2 != null) {
            Long expressUid = Long.parseLong(p2);
            result = service.deliver(detailId, expressUid);
        } else if (p3 != null){
            result = service.pay(detailId);
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
}
