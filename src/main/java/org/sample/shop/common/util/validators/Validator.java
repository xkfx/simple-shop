package org.sample.shop.common.util.validators;

public interface Validator {

    String getName();

    void validate(Object o);

    /**
     * 便于多级查找校验方法，如果不支持该功能，
     * 直接内部调void validate(Object o);方法即可
     */
    void validate(Object o, String validatorName);
}
