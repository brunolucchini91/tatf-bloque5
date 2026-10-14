package com.tatf.adminces.modules.register.test;

import com.tatf.adminces.modules.access.task.AccessTask;
import com.tatf.adminces.modules.alert.data.AlertData;
import com.tatf.adminces.modules.alert.task.AlertTask;
import com.tatf.adminces.modules.base.BaseTest;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.register.task.RegisterTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Crear cuenta Administrador")
    public void crearAdministradorTest() {

        String email =
                "admin" + System.currentTimeMillis() + "@gmail.com";

        String password = "Admin123";

        access.enterToSystem();
        access.verifyAccessPage();

        register.enterToRegister();
        register.verifyRegisterPage();

        register.createAdministrator(
                "Bruno",
                "Admin",
                email,
                password,
                "Uruguay"
        );

        alert.verify(
                AlertData.SUCCESS_TITLE,
                "Usuario creado."
        );

        alert.confirm();

        login.enterToLogin();
        login.verifyLoginPage();
        login.login(email, password);

        alert.verify(
                AlertData.SUCCESS_TITLE,
                "Sesión iniciada."
        );
    }
}