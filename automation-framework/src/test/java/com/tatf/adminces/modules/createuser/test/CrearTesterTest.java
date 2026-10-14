package com.tatf.adminces.modules.createuser.test;

import com.tatf.adminces.modules.access.task.AccessTask;
import com.tatf.adminces.modules.alert.data.AlertData;
import com.tatf.adminces.modules.alert.task.AlertTask;
import com.tatf.adminces.modules.base.BaseTest;
import com.tatf.adminces.modules.createuser.data.CreateUserData;
import com.tatf.adminces.modules.createuser.task.CreateUserTask;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.register.task.RegisterTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CrearTesterTest extends BaseTest {

    private AccessTask access;
    private RegisterTask register;
    private AlertTask alert;
    private LoginTask login;
    private CreateUserTask createUser;

    @BeforeEach
    public void configurar() {
        access = new AccessTask(browser);
        register = new RegisterTask(browser);
        alert = new AlertTask(browser);
        login = new LoginTask(browser);
        createUser = new CreateUserTask(browser);
    }

    @Test
    @DisplayName("Crear cuenta Tester")
    public void crearTesterTest() {

        String adminEmail =
                "admin" + System.currentTimeMillis() + "@gmail.com";

        String testerEmail =
                "tester" + System.currentTimeMillis() + "@gmail.com";

        String adminPassword = "Admin123";

        access.enterToSystem();

        register.enterToRegister();

        register.createAdministrator(
                "Bruno",
                "Admin",
                adminEmail,
                adminPassword,
                "Uruguay"
        );

        alert.verify(
                AlertData.SUCCESS_TITLE,
                "Usuario creado."
        );

        alert.confirm();

        login.enterToLogin();
        login.login(adminEmail, adminPassword);

        alert.verify(
                AlertData.SUCCESS_TITLE,
                "Sesión iniciada."
        );

        alert.confirm();

        createUser.enterToCreateUser();
        createUser.verifyCreateUserPage();

        createUser.createTester(
                "Bruno",
                "Tester",
                testerEmail,
                "Uruguay",
                "Tester123",
                "testerJunior"
        );

        alert.verify(
                AlertData.SUCCESS_TITLE,
                CreateUserData.SUCCESS_MESSAGE
        );
    }
}