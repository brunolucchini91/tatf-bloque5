package com.tatf.adminces.modules.resetpassword.test;

import com.tatf.adminces.modules.access.task.AccessTask;
import com.tatf.adminces.modules.alert.data.AlertData;
import com.tatf.adminces.modules.alert.task.AlertTask;
import com.tatf.adminces.modules.base.BaseTest;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.register.task.RegisterTask;
import com.tatf.adminces.modules.resetpassword.data.ResetPasswordData;
import com.tatf.adminces.modules.resetpassword.task.ResetPasswordTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReiniciarContrasenaTest extends BaseTest {

    private AccessTask access;
    private RegisterTask register;
    private AlertTask alert;
    private ResetPasswordTask resetPassword;
    private LoginTask login;

    @BeforeEach
    public void configurar() {
        access = new AccessTask(browser);
        register = new RegisterTask(browser);
        alert = new AlertTask(browser);
        resetPassword = new ResetPasswordTask(browser);
        login = new LoginTask(browser);
    }

    @Test
    @DisplayName("Reiniciar contraseña")
    public void reiniciarContrasenaTest() {

        String email =
                "admin" + System.currentTimeMillis() + "@gmail.com";

        String oldPassword = "Admin123";
        String newPassword = "Admin456";

        access.enterToSystem();
        access.verifyAccessPage();

        register.enterToRegister();

        register.createAdministrator(
                "Bruno",
                "Admin",
                email,
                oldPassword,
                "Uruguay"
        );

        alert.verify(
                AlertData.SUCCESS_TITLE,
                "Usuario creado."
        );

        alert.confirm();

        resetPassword.enterToResetPassword();
        resetPassword.verifyResetPasswordPage();

        resetPassword.resetPassword(
                email,
                newPassword
        );

        alert.verify(
                AlertData.SUCCESS_TITLE,
                ResetPasswordData.SUCCESS_MESSAGE
        );

        alert.confirm();

        login.enterToLogin();
        login.verifyLoginPage();
        login.login(email, newPassword);

        alert.verify(
                AlertData.SUCCESS_TITLE,
                "Sesión iniciada."
        );
    }
}