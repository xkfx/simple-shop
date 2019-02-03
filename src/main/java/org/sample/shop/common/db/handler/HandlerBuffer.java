package org.sample.shop.common.db.handler;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.sample.shop.common.annotation.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class HandlerBuffer {

    /**
     * TODO map加载完之后就维持不变了，不同步是否可行呢？
     */
    private static Map<String, ResultSetHandler> map = new HashMap<>();

    public static ResultSetHandler getHandler(String type) throws ClassNotFoundException {
        ResultSetHandler handler = map.get(type);
        if (handler != null) {
            return handler;
        } else if (!type.equals("java.lang.Number")) {
            Class<?> c = Class.forName(type);
            handler = new BeanListHandler<>(c);
            map.put(type, handler);
        } else {
            handler = new MapListHandler();
            map.put(type, handler);
        }
        return handler;
    }
}
