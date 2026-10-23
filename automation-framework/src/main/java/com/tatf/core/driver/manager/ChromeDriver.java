package com.tatf.core.driver.manager;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriver extends DriverManager {
    /**
     * Crea el driver de Chrome con las opciones por defecto.
     */
    public ChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-features=HttpsUpgrades,HttpsFirstModeV2,HttpsFirstBalancedMode,"
                + "HttpsFirstBalancedModeAutoEnable,HttpsFirstModeV2ForTypicallySecureUsers,HttpsFirstModeIncognito");

        this.driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
        setDefaultConfig();
    }
}
