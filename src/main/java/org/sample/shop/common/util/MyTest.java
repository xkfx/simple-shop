package org.sample.shop.common.util;

import org.sample.shop.common.util.validators.RegexpValidator;

public class MyTest {

    private static final EasyValidator EASY_VALIDATOR = new EasyValidator();

    public static void main(String[] args) {
        EASY_VALIDATOR.addValidator(new RegexpValidator());
        int type = 3;
        String username = "           ";
        String password = "32";
        EASY_VALIDATOR.check(type, "type.user");
        EASY_VALIDATOR.check(username, "username.regexp");
        EASY_VALIDATOR.check(password, "password.regexp");

    }
}
