package com.tatf.adminces.modules.alert.pom;

import com.tatf.core.browser.IBrowser;

public class AlertPO {

    private final IBrowser browser;

    private final String title = "swal2-title";
    private final String message = "swal2-html-container";
    private final String confirmButton = "button.swal2-confirm";

    public AlertPO(IBrowser browser) {
        this.browser = browser;
    }

    public void waitForAlert() {
        browser.wait(title).id();
    }

    public String getTitle() {
        return browser.find().id(title).getText();
    }

    public String getMessage() {
        return browser.find().id(message).getText();
    }

    public void clickConfirm() {
        browser.wait(confirmButton).css();
        browser.find().css(confirmButton).click();
    }
}