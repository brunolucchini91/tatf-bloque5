package com.tatf.adminces.modules.login.pom;

import com.tatf.core.browser.IBrowser;

public class LoginPO {

    private final IBrowser browser;

    private final String emailInput = "inputEmail";
    private final String passwordInput = "inputPassword";
    private final String loginButton =
            "//button[normalize-space()='Iniciar Sesión']";

    private final String title =
            "//h5[contains(@class,'title')]";

    public LoginPO(IBrowser browser) {
        this.browser = browser;
    }

    public void waitForPage() {
        browser.wait(emailInput).name();
    }

    public String getTitle() {
        return browser.find().xpath(title).getText();
    }

    public void enterEmail(String email) {
        browser.find().name(emailInput).write(email);
    }

    public void enterPassword(String password) {
        browser.find().name(passwordInput).write(password);
    }

    public void clickLogin() {
        browser.find().xpath(loginButton).click();
    }
}