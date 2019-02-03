package org.sample.shop.common.util;

import org.apache.commons.lang3.StringUtils;
import org.sample.shop.common.aspect.ServiceMethodAspect;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;

import java.util.Map;
import java.util.Properties;

public class PresentationUtils {

    private static final Map<String, String> MAP = new HashMap<>();

    private static final String FILE_NAME = "presentation.properties";

    static {
        // 把.properties文件里的键值对读到内存里
        Properties prop = new Properties();
        String filename = File.separator + FILE_NAME;
        InputStream inputStream = ServiceMethodAspect.class.getClassLoader().getResourceAsStream(filename);
        if (inputStream == null) throw new RuntimeException("Sorry, unable to find " + filename);
        try (InputStreamReader input = new InputStreamReader(inputStream, StandardCharsets.UTF_8)){
            prop.load(input);
            Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                MAP.put(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException("An exception occurred while reading " + filename, e);
        }
    }

    public static String english2chinese(String english) {
        String chinese = MAP.get(english);
        if (StringUtils.isBlank(chinese)) throw new RuntimeException("Chinese presentation not found, word=" + english);
        return chinese;
    }
}
