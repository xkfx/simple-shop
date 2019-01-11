package org.sample.shop.common.util;

import org.sample.shop.merchant.service.ItemService;
import org.sample.shop.merchant.service.impl.ItemServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * 供servlet调用的工具类
 */
public class ServletUtils {

    private static class Parser {

    }

    public static Object[] parseParameters(HttpServletRequest req, String[] names, Class<?>[] types) {
        return  null;
    }

    public static <T> Object invokeService(T service, String methodName, Object[] parameters) {
        return null;
    }

    private static ItemService service = new ItemServiceImpl();

    // 反射的性能代价  。。以及
    public static void main(String[] args) {
        // 妈的 为啥不直接提供个方法， 说我试图调用这个就好了 ，不是有方法引用嘛
        String[] parameterNames = new String[] {"uid", "status", "start", "offset"};
        Class<?>[] parameterTypes = new Class[] {Long.class, Integer.class, Integer.class, Integer.class};
        // ServletUtils.invokeService(service, "listByUidAndStatusNew", parameterNames, parameterTypes);
    }
}
