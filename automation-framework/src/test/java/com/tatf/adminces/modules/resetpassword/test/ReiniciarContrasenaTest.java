package com.tatf.adminces.modules.resetpassword.test;

import com.tatf.adminces.modules.access.task.AccessTask;
import com.tatf.adminces.modules.alert.data.AlertData;
import com.tatf.adminces.modules.alert.task.AlertTask;
import com.tatf.adminces.modules.base.BaseTest;
import com.tatf.adminces.modules.base.DataGenerator;
import com.tatf.adminces.modules.login.data.LoginData;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.register.data.RegisterData;
import com.tatf.adminces.modules.register.task.RegisterTask;
import com.tatf.adminces.modules.resetpassword.data.ResetPasswordData;
import com.tatf.adminces.modules.resetpassword.task.ResetPasswordTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@Tag("administrador")
@Tag("regresion")
@DisplayName("Reiniciar contraseña")
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

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(resources = "/data/reinicio_contrasena.csv", useHeadersInDisplayName = true)
    public void reiniciarContrasenaTest(String nombre, String apellido, String pais,
                                        String contrasenaActual, String contrasenaNueva) {
        String email = DataGenerator.uniqueEmail(RegisterData.EMAIL_PREFIX);

        access.enterToSystem();
        access.verifyAccessPage();

        register.enterToRegister();
        register.createAdministrator(nombre, apellido, email, contrasenaActual, pais);
        alert.verify(AlertData.SUCCESS_TITLE, RegisterData.SUCCESS_MESSAGE);
        alert.confirm();

        resetPassword.enterToResetPassword();
        resetPassword.verifyResetPasswordPage();
        resetPassword.resetPassword(email, contrasenaNueva);
        alert.verify(AlertData.SUCCESS_TITLE, ResetPasswordData.SUCCESS_MESSAGE);
        alert.confirm();

        login.enterToLogin();
        login.verifyLoginPage();
        login.login(email, contrasenaNueva);
        alert.verify(AlertData.SUCCESS_TITLE, LoginData.SUCCESS_MESSAGE);
    }
}
