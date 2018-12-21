package org.sample.shop.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.sample.shop.dto.ServiceResult;

import javax.servlet.http.HttpServletResponse;

public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtil() {

    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }

    public static String restfulResponse(HttpServletResponse resp, ServiceResult result) throws JsonProcessingException {
        String jsonString;
        if (result.isSuccess()) { // 成功返回数据
            jsonString = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(result.getData());
        } else { // 失败返回理由
            ObjectNode root = MAPPER.createObjectNode();
            root.put(JsonFieldName.MESSAGE, result.getDescription());
            jsonString = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        }
        resp.setStatus(result.getStatus());
        return jsonString;
    }
}
