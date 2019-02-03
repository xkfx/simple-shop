package org.sample.shop.common.util;

import org.apache.commons.lang3.StringUtils;
import org.sample.shop.common.exception.ValidatorException;
import org.sample.shop.common.util.validators.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的一个简易参数校验器
 */
public class EasyValidator {
    /**
     * 初始化后不再更新，否则在多线程环境下有风险
     */
    private static final Map<String, Validator> MAP = new HashMap<>();

    public void addValidator(Validator validator) {
        MAP.put(validator.getName(), validator);
    }

    public void check(Object object, String validatorName) {
        Validator validator = MAP.get(getLast(validatorName));
        if (validator != null) {
            validator.validate(object, removeLast(validatorName));
        } else {
            throw new ValidatorException("validator not found, validatorName=" + validatorName);
        }
    }

    /**
     * 获取顶级校验器名称，如果只有一级就原样返回
     */
    private static String getLast(String validatorName) {
        int index = StringUtils.lastIndexOf(validatorName, '.');
        if (index == -1) return validatorName;
        return StringUtils.substring(validatorName, index + 1);
    }

    /**
     * 去掉顶级校验器名称，以便向下传递，如果只有一级就原样返回
     */
    private static String removeLast(String validatorName) {
        int index = StringUtils.lastIndexOf(validatorName, '.');
        if (index == -1) return validatorName;
        return StringUtils.substring(validatorName, 0, index);
    }
}
