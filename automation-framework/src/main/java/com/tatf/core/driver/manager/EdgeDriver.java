package com.tatf.core.driver.manager;

import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriver extends DriverManager {

    /**
     * Crea el driver de Edge con las opciones por defecto.
     */
    public EdgeDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("start-maximized");
        edgeOptions.addArguments("--ignore-certificate-errors");
        edgeOptions.addArguments("--disable-features=HttpsUpgrades,HttpsFirstModeV2,HttpsFirstBalancedMode,"
                + "HttpsFirstBalancedModeAutoEnable,HttpsFirstModeV2ForTypicallySecureUsers,HttpsFirstModeIncognito");

        this.driver = new org.openqa.selenium.edge.EdgeDriver(edgeOptions);
        setDefaultConfig();
    }
}
