package com.tatf.adminces.modules.createuser.task;

import com.tatf.adminces.modules.createuser.data.CreateUserData;
import com.tatf.adminces.modules.createuser.pom.CreateUserPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

public class CreateUserTask {

    private final IBrowser browser;
    private final CreateUserPO createUser;

    public CreateUserTask(IBrowser browser) {
        this.browser = browser;
        this.createUser = new CreateUserPO(browser);
    }

    public void enterToCreateUser() {
        browser.find()
                .xpath("//a[@href='/adminces/create-user']")
                .click();

        createUser.waitForPage();
    }

    public void verifyCreateUserPage() {
        IVerify.create().verify(
                CreateUserData.TITLE,
                createUser.getTitle(),
                "No se accedió a la página de creación de Tester."
        );
    }

    public void createTester(
            String firstName,
            String lastName,
            String email,
            String country,
            String password,
            String profile) {

        createUser.enterFirstName(firstName);
        createUser.enterLastName(lastName);
        createUser.enterEmail(email);
        createUser.selectCountry(country);
        createUser.enterPassword(password);
        createUser.selectProfile(profile);
        createUser.clickRegister();
    }
}