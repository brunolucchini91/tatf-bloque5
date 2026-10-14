package com.tatf.adminces.modules.createuser.pom;

import com.tatf.core.browser.IBrowser;

public class CreateUserPO {

    private final IBrowser browser;

    private final String firstNameInput = "inputFirstName";
    private final String lastNameInput = "inputLastName";
    private final String emailInput = "inputEmail";
    private final String countryInput = "inputCountry";
    private final String passwordInput = "inputPassword";
    private final String registerButton = "btnRegister";

    private final String title =
            "//h5[contains(@class,'title')]";

    public CreateUserPO(IBrowser browser) {
        this.browser = browser;
    }

    public void waitForPage() {
        browser.wait(firstNameInput).name();
    }

    public String getTitle() {
        return browser.find().xpath(title).getText();
    }

    public void enterFirstName(String firstName) {
        browser.find().name(firstNameInput).write(firstName);
    }

    public void enterLastName(String lastName) {
        browser.find().name(lastNameInput).write(lastName);
    }

    public void enterEmail(String email) {
        browser.find().name(emailInput).write(email);
    }

    public void selectCountry(String country) {
        browser.find().name(countryInput).selectText(country);
    }

    public void enterPassword(String password) {
        browser.find().name(passwordInput).write(password);
    }

    public void selectProfile(String profile) {
        browser.find().xpath(
                "//label[@for='" + profile + "']"
        ).click();
    }

    public void clickRegister() {
        browser.find().id(registerButton).click();
    }
}