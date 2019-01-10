package org.sample.shop.web.controller.v1;

import org.sample.shop.dto.JsonError;
import org.sample.shop.entity.TransportOrder;
import org.sample.shop.dto.ServiceResult;
import org.sample.shop.service.TransportService;
import org.sample.shop.service.impl.TransportServiceImpl;
import org.sample.shop.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TransportController extends HttpServlet {

    private TransportService transportService = TransportServiceImpl.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 拿约定好的参数，暂时不管权限管理。。。。。
        final String param2 = req.getParameter("uid");
        final String param3 = req.getParameter("start");
        final String param4 = req.getParameter("offset");
        final long uid = Long.parseLong(param2);
        final int start = Integer.parseInt(param3);
        final int offset = Integer.parseInt(param4);
        // 用参数调服务
        ServiceResult<List<TransportOrder>> result = transportService.getByUid(uid, start, offset);
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
        super.doPost(req, resp);
    }
}
