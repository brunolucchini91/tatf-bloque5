package com.tatf.adminces.modules.resetpassword.task;

import com.tatf.adminces.modules.menu.pom.MenuPO;
import com.tatf.adminces.modules.resetpassword.data.ResetPasswordData;
import com.tatf.adminces.modules.resetpassword.pom.ResetPasswordPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

public class ResetPasswordTask {
    private final MenuPO menu;
    private final ResetPasswordPO resetPassword;

    public ResetPasswordTask(IBrowser browser) {
        this.menu = new MenuPO(browser);
        this.resetPassword = new ResetPasswordPO(browser);
    }

    public void enterToResetPassword() {
        menu.clickResetPassword();
        resetPassword.waitForPage();
    }

    public void verifyResetPasswordPage() {
        IVerify.create().verify(
                ResetPasswordData.TITLE,
                resetPassword.getTitle(),
                "No se accedió a la página de reinicio de contraseña."
        );
    }

    public void resetPassword(String email, String newPassword) {
        resetPassword.enterEmail(email);
        resetPassword.enterPassword(newPassword);
        resetPassword.repeatPassword(newPassword);
        resetPassword.clickReset();
    }
}
