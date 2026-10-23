package com.tatf.adminces.modules.login.task;

import com.tatf.adminces.modules.login.data.LoginData;
import com.tatf.adminces.modules.login.pom.LoginPO;
import com.tatf.adminces.modules.menu.pom.MenuPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

public class LoginTask {
    private final MenuPO menu;
    private final LoginPO login;

    public LoginTask(IBrowser browser) {
        this.menu = new MenuPO(browser);
        this.login = new LoginPO(browser);
    }

    public void enterToLogin() {
        menu.clickLogin();
        login.waitForPage();
    }

    public void verifyLoginPage() {
        IVerify.create().verify(
                LoginData.TITLE,
                login.getTitle().toUpperCase(),
                "No se accedió a la página de inicio de sesión."
        );
    }

    public void login(String email, String password) {
        login.enterEmail(email);
        login.enterPassword(password);
        login.clickLogin();
    }
}
