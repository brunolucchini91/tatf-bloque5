package com.tatf.adminces.modules.access.data;

import com.tatf.core.util.ConfigReader;

public class AccessData {
    private static final ConfigReader CONFIG = new ConfigReader("adminces.properties");

    public static final String URL = CONFIG.asString("adminces.url");
    public static final String ACCESS_HASH = CONFIG.asString("adminces.access_hash");
    public static final String TITLE = "ACCESOS";
}
