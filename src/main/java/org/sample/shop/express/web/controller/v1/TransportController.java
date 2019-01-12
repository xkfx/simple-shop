package org.sample.shop.express.web.controller.v1;

import org.sample.shop.common.dto.JsonError;
import org.sample.shop.common.entity.TransportOrder;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.enums.business.BusinessCode;
import org.sample.shop.express.service.TransportService;
import org.sample.shop.express.service.impl.TransportServiceImpl;
import org.sample.shop.common.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TransportController extends HttpServlet {

    private TransportService transportService = new TransportServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 拿约定好的参数，暂时不管权限管理。。。。。
        final String param2 = req.getParameter("uid");
        final String param3 = req.getParameter("start");
        final String param4 = req.getParameter("offset");
        final String p5 = req.getParameter("detailId");

        ServiceResult result = new ServiceResult(BusinessCode.INNER_ERROR);
        if (param2 != null) {
            final long uid = Long.parseLong(param2);
            final int start = Integer.parseInt(param3);
            final int offset = Integer.parseInt(param4);
            // 用参数调服务
            result = transportService.getByUid(uid, start, offset);
        } else if (p5 != null) {
            final long detailId = Long.parseLong(p5);
            result = transportService.getByDetailId(detailId);
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
        // update location
        // update to waiting/travelling/delivering/finish
        final String p0 = req.getParameter("expressId");
        final String p1 = req.getParameter("location");
        final String p2 = req.getParameter("waiting");
        final String p3 = req.getParameter("travelling");
        final String p4 = req.getParameter("delivering");
        final String p5 = req.getParameter("finish");

        Long expressId = Long.parseLong(p0);
        ServiceResult result = new ServiceResult(BusinessCode.INNER_ERROR);
        if (p1 != null) {
            result = transportService.updateLocation(expressId, p1);
        } else if (p2 != null) {
            result = transportService.updateToWait(expressId);
        } else if (p3 != null) {
            result = transportService.updateToTravel(expressId);
        } else if (p4 != null) {
            result = transportService.updateToDeliver(expressId);
        } else if (p5 != null) {
            result = transportService.finishOrder(expressId);
        }

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
}
