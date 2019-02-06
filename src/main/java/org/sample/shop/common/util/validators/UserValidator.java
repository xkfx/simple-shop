package org.sample.shop.common.util.validators;

import org.sample.shop.common.exception.ValidateException;
import org.sample.shop.common.exception.ValidatorException;

public class UserValidator implements Validator {

    private static final String NAME = "user";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void validate(Object o) {
        throw new ValidatorException("UserValidator的validate(Object o)方法未定义");
    }

    @Override
    public void validate(Object o, String validatorName) {
        if ("type".equals(validatorName)) {
            int type = (Integer) o;
            if (type < 0 || type > 2) {
                throw new ValidateException("用户类型不合法！");
            }
        } else {
            throw ValidatorException.undefined(validatorName);
        }
    }
}
