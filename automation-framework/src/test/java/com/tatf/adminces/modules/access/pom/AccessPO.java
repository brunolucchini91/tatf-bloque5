package com.tatf.adminces.modules.access.pom;

import com.tatf.core.browser.IBrowser;

public class AccessPO {

    private final IBrowser browser;

    private final String passwordInput = "pass";
    private final String enterButton =
            "//button[normalize-space()='Ingresar']";
    private final String title =
            "//h4[normalize-space()='Accesos']";

    public AccessPO(IBrowser browser) {
        this.browser = browser;
    }

    public void waitForPasswordInput() {
        browser.wait(passwordInput).id();
    }

    public void enterPassword(String password) {
        browser.find().id(passwordInput).write(password);
    }

    public void clickEnter() {
        browser.find().xpath(enterButton).click();
    }

    public void waitForTitle() {
        browser.wait(title).xpath();
    }

    public String getTitle() {
        return browser.find().xpath(title).getText();
    }
}