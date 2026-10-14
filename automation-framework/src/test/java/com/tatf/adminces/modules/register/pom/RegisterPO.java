package com.tatf.adminces.modules.register.pom;

import com.tatf.core.browser.IBrowser;

public class RegisterPO {

    private final IBrowser browser;

    private final String firstNameInput = "inputFirstName";
    private final String lastNameInput = "inputLastName";
    private final String emailInput = "inputEmail";
    private final String passwordInput = "inputPassword";
    private final String repeatPasswordInput = "inputRepeatPassword";
    private final String countryInput = "inputCountry";
    private final String registerButton = "btnRegister";

    private final String title =
            "//h5[normalize-space()='Crear cuenta Administrador']";

    public RegisterPO(IBrowser browser) {
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

    public void enterPassword(String password) {
        browser.find().name(passwordInput).write(password);
    }

    public void repeatPassword(String password) {
        browser.find().name(repeatPasswordInput).write(password);
    }

    public void enterCountry(String country) {
        browser.find().name(countryInput).write(country);
    }

    public void clickRegister() {
        browser.find().id(registerButton).click();
    }
}
