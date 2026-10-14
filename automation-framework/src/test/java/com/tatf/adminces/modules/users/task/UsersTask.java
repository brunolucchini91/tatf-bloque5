package com.tatf.adminces.modules.users.task;

import com.tatf.adminces.modules.users.data.UsersData;
import com.tatf.adminces.modules.users.pom.UsersPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

public class UsersTask {

    private final IBrowser browser;
    private final UsersPO users;

    public UsersTask(IBrowser browser) {
        this.browser = browser;
        this.users = new UsersPO(browser);
    }

    public void enterToUsers() {
        browser.find()
                .xpath("//a[@href='/adminces/view-users']")
                .click();

        users.waitForPage();
    }

    public void verifyUsersPage() {
        IVerify.create().verify(
                UsersData.TITLE,
                users.getTitle(),
                "No se accedió a la página de usuarios."
        );
    }

    public void verifyUserExists(String email) {
        IVerify.create().verify(
                true,
                users.getTableText().contains(email),
                "El usuario no aparece en la lista."
        );
    }

    public void deleteUser(String email) {
        users.scrollToUser(email);
        users.clickDeleteUser(email);
    }

    public void verifyUserDoesNotExist(String email) {
        IVerify.create().verify(
                false,
                users.getTableText().contains(email),
                "El usuario continúa apareciendo en la lista."
        );
    }
}