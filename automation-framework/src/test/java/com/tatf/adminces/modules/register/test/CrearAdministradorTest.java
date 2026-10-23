package com.tatf.adminces.modules.register.test;

import com.tatf.adminces.modules.access.task.AccessTask;
import com.tatf.adminces.modules.alert.data.AlertData;
import com.tatf.adminces.modules.alert.task.AlertTask;
import com.tatf.adminces.modules.base.BaseTest;
import com.tatf.adminces.modules.base.DataGenerator;
import com.tatf.adminces.modules.login.data.LoginData;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.register.data.RegisterData;
import com.tatf.adminces.modules.register.task.RegisterTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@Tag("administrador")
@Tag("regresion")
@DisplayName("Crear cuenta Administrador")
public class CrearAdministradorTest extends BaseTest {
    private AccessTask access;
    private RegisterTask register;
    private AlertTask alert;
    private LoginTask login;

    @BeforeEach
    public void configurar() {
        access = new AccessTask(browser);
        register = new RegisterTask(browser);
        alert = new AlertTask(browser);
        login = new LoginTask(browser);
    }

    @Tag("humo")
    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(resources = "/data/administradores.csv", useHeadersInDisplayName = true)
    public void crearAdministradorTest(String nombre, String apellido, String contrasena, String pais) {
        String email = DataGenerator.uniqueEmail(RegisterData.EMAIL_PREFIX);

        access.enterToSystem();
        access.verifyAccessPage();

        register.enterToRegister();
        register.verifyRegisterPage();
        register.createAdministrator(nombre, apellido, email, contrasena, pais);
        alert.verify(AlertData.SUCCESS_TITLE, RegisterData.SUCCESS_MESSAGE);
        alert.confirm();

        login.enterToLogin();
        login.verifyLoginPage();
        login.login(email, contrasena);
        alert.verify(AlertData.SUCCESS_TITLE, LoginData.SUCCESS_MESSAGE);
    }
}
