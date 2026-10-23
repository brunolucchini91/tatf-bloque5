package com.tatf.adminces.modules.base;

import com.tatf.core.util.ConfigReader;

public class DataGenerator {
    private static final String EMAIL_DOMAIN =
            new ConfigReader("adminces.properties").asString("adminces.email_domain");

    private DataGenerator() {
    }

    public static String uniqueEmail(String prefix) {
        return prefix + System.currentTimeMillis() + "@" + EMAIL_DOMAIN;
    }
}
