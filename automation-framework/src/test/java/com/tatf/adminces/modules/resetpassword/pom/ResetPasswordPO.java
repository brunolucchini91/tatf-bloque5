package com.tatf.adminces.modules.resetpassword.pom;

import com.tatf.core.browser.IBrowser;

public class ResetPasswordPO {

    private final IBrowser browser;

    private final String emailInput = "inputEmail";
    private final String passwordInput = "inputPassword";
    private final String repeatPasswordInput = "inputRepeatPassword";
    private final String resetButton = "btnReset";

    private final String title =
            "//h5[contains(@class,'title')]";

    public ResetPasswordPO(IBrowser browser) {
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

    public void repeatPassword(String password) {
        browser.find().name(repeatPasswordInput).write(password);
    }

    public void clickReset() {
        browser.find().id(resetButton).click();
    }
}