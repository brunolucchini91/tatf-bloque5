package com.tatf.adminces.modules.menu.pom;

import com.tatf.core.browser.IBrowser;

public class MenuPO {
    private final IBrowser browser;

    private final String registerLink = "//a[@href='/adminces/register']";
    private final String loginLink = "//a[@href='/adminces/login']";
    private final String resetPasswordLink = "//a[@href='/adminces/forgot-password']";
    private final String createUserLink = "//a[@href='/adminces/create-user']";
    private final String usersLink = "//a[@href='/adminces/view-users']";

    public MenuPO(IBrowser browser) {
        this.browser = browser;
    }

    public void clickRegister() {
        browser.find().xpath(registerLink).click();
    }

    public void clickLogin() {
        browser.find().xpath(loginLink).click();
    }

    public void clickResetPassword() {
        browser.find().xpath(resetPasswordLink).click();
    }

    public void clickCreateUser() {
        browser.find().xpath(createUserLink).click();
    }

    public void clickUsers() {
        browser.find().xpath(usersLink).click();
    }
}
