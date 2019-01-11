package org.sample.shop.merchant.web.controller.v1;

import org.sample.shop.common.dto.JsonError;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.OrderDetail;
import org.sample.shop.merchant.service.OrderDetailService;
import org.sample.shop.merchant.service.impl.OrderDetailServiceImpl;
import org.sample.shop.common.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OrderDetailController extends HttpServlet {

    private OrderDetailService service = new OrderDetailServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String p1 = req.getParameter("merchantId");
        Long merchantId = Long.parseLong(p1);

        ServiceResult<List<OrderDetail>> result = service.getByMerchantId(merchantId);

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


        super.doPost(req, resp);
    }
}
