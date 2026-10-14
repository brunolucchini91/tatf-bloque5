package com.tatf.adminces.modules.register.task;

import com.tatf.adminces.modules.register.data.RegisterData;
import com.tatf.adminces.modules.register.pom.RegisterPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

public class RegisterTask {

    private final IBrowser browser;
    private final RegisterPO register;

    public RegisterTask(IBrowser browser) {
        this.browser = browser;
        this.register = new RegisterPO(browser);
    }

    public void enterToRegister() {
        browser.find()
                .xpath("//a[@href='/adminces/register']")
                .click();

        register.waitForPage();
    }

    public void verifyRegisterPage() {
        IVerify.create().verify(
                RegisterData.TITLE,
                register.getTitle().toUpperCase(),
                "No se accedió a la página de registro."
        );
    }

    public void createAdministrator(
            String firstName,
            String lastName,
            String email,
            String password,
            String country) {

        register.enterFirstName(firstName);
        register.enterLastName(lastName);
        register.enterEmail(email);
        register.enterPassword(password);
        register.repeatPassword(password);
        register.enterCountry(country);
        register.clickRegister();
    }
}