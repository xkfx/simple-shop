package org.sample.shop;

import org.sample.shop.entity.Item;
import org.sample.shop.service.ItemService;
import org.sample.shop.dto.ServiceResult;
import org.sample.shop.service.impl.ItemServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private static ItemService itemService = new ItemServiceImpl();

    public static <T> T get(Class<T> type, String parameter){
        return type.cast(parameter);
    }

//    public void tryToGetParameters(HttpServletRequest req, String[] args, Class<?>[] parametersTypes) {
//        ArrayList parameters = new ArrayList();
//        for (int i = 0; i != args.length; ++i) {
//            String result = req.getParameter(args[i]);
//            if (result != null) {
//                parameters.add(get(parametersTypes[i], result));
//            }
//        }
//    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        final String strUid = "1002";
        final String strStatus = "0";
        final String strStart = null;
        final String strOffset = null;
//        Object obj = get(Long.class, strUid);
        ArrayList parameters = new ArrayList();
        if (strUid != null) {
            final Long uid = Long.parseLong(strUid);
            parameters.add(uid);
        }
        if (strStatus != null) {
            final Integer status = Integer.parseInt(strStatus);
            parameters.add(status);
        }
        if (strStart != null) {
            final Integer start = Integer.parseInt(strStart);
            parameters.add(start);
        }
        if (strOffset != null) {
            final Integer offset = Integer.parseInt(strOffset);
            parameters.add(offset);
        }
        Class<?>[] parametersTypes = new Class<?>[parameters.size()];
        for (int i = 0; i != parameters.size(); ++i) {
            parametersTypes[i] = parameters.get(i).getClass();
        }
        System.out.println(ItemServiceImpl.class);
        Method m = ItemService.class.getMethod("listByUidAndStatusNew", parametersTypes);

        ServiceResult<List<Item>> result = (ServiceResult<List<Item>>) m.invoke(itemService, parameters.toArray());
        System.out.println(result.getData());
//        ArrayList<Class<?>> listParameterType = new ArrayList<>();
//        if (status != null) {
//            listParameterType.add(status.getClass());
//        }
//        Class<?>[] arrayParameterType = (Class<?>[]) listParameterType.toArray();
//        // 可以用高阶函数改造
//
//        try {
//            Method m = ItemServiceImpl.class.getClass().getMethod("listByUidAndStatusNew", arrayParameterType);
//            ServiceResult<List<Item>> result = (ServiceResult<List<Item>>) m.invoke(itemService, arrayParameterType);
//            System.out.println(result.getData());
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }
}
