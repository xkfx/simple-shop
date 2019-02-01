package org.sample.shop.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static ObjectNode createObjectNode() {
        return MAPPER.createObjectNode();
    }

    public static <T extends JsonNode> ObjectNode valueToTree(Object obj) {
        return MAPPER.valueToTree(obj);
    }

    public static ObjectNode fail(String error) {
        ObjectNode jsonNodes = MAPPER.createObjectNode();
        jsonNodes.put("message", error);
        return jsonNodes;
    }
}
