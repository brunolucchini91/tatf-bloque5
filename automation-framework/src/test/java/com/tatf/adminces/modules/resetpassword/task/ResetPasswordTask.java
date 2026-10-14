package com.tatf.adminces.modules.resetpassword.task;

import com.tatf.adminces.modules.resetpassword.data.ResetPasswordData;
import com.tatf.adminces.modules.resetpassword.pom.ResetPasswordPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

public class ResetPasswordTask {

    private final IBrowser browser;
    private final ResetPasswordPO resetPassword;

    public ResetPasswordTask(IBrowser browser) {
        this.browser = browser;
        this.resetPassword = new ResetPasswordPO(browser);
    }

    public void enterToResetPassword() {
        browser.find()
                .xpath("//a[@href='/adminces/forgot-password']")
                .click();

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