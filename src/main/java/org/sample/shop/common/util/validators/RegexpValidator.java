package org.sample.shop.common.util.validators;

import org.apache.commons.lang3.StringUtils;
import org.sample.shop.common.aspect.ServiceMethodAspect;
import org.sample.shop.common.exception.ValidateException;
import org.sample.shop.common.exception.ValidatorException;
import org.sample.shop.common.util.PresentationUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class RegexpValidator implements Validator {

    private static final String NAME = "regexp";

    private static final String FILE_NAME = File.separator + "easy-validator.properties";

    private static final Map<String, Pattern> MAP = new HashMap<>();

    static {
        // 把.properties文件里的键值对读到内存里
        Properties prop = new Properties();
        try (InputStream input = ServiceMethodAspect.class.getClassLoader().getResourceAsStream(FILE_NAME)){
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find " + FILE_NAME);
            }
            prop.load(input);
            Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                MAP.put(key, Pattern.compile(value));
            }
        } catch (IOException e) {
            throw new RuntimeException("An exception occurred while reading " + FILE_NAME, e);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void validate(Object o) {
        throw new ValidatorException("RegexpValidator的validate(Object o)方法未定义");
    }

    @Override
    public void validate(Object o, String validatorName) {
        String str = String.valueOf(o);
        if (StringUtils.isBlank(str)) {
            throw new ValidateException(PresentationUtils.english2chinese(validatorName) + "不能为空");
        } else {
            Pattern pattern = MAP.get(validatorName);
            // 程序员要确保格式已经定义
            if (pattern == null) throw new ValidatorException(validatorName + "校验器未定义");
            // 格式检查
            if (!pattern.matcher(str).matches()) {
                throw new ValidateException(PresentationUtils.english2chinese(validatorName) + "格式不正确");
            }
        }
    }
}
