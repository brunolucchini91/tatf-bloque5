package com.tatf.adminces.modules.createuser.test;

import com.tatf.adminces.modules.access.task.AccessTask;
import com.tatf.adminces.modules.alert.data.AlertData;
import com.tatf.adminces.modules.alert.task.AlertTask;
import com.tatf.adminces.modules.base.BaseTest;
import com.tatf.adminces.modules.base.DataGenerator;
import com.tatf.adminces.modules.createuser.data.CreateUserData;
import com.tatf.adminces.modules.createuser.task.CreateUserTask;
import com.tatf.adminces.modules.login.data.LoginData;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.register.data.RegisterData;
import com.tatf.adminces.modules.register.task.RegisterTask;
import com.tatf.adminces.modules.users.task.UsersTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@Tag("tester")
@Tag("regresion")
@DisplayName("Crear cuenta Tester")
public class CrearTesterTest extends BaseTest {
    private AccessTask access;
    private RegisterTask register;
    private AlertTask alert;
    private LoginTask login;
    private CreateUserTask createUser;
    private UsersTask users;

    @BeforeEach
    public void configurar() {
        access = new AccessTask(browser);
        register = new RegisterTask(browser);
        alert = new AlertTask(browser);
        login = new LoginTask(browser);
        createUser = new CreateUserTask(browser);
        users = new UsersTask(browser);
    }

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(resources = "/data/testers.csv", useHeadersInDisplayName = true)
    public void crearTesterTest(String nombre, String apellido, String pais,
                                String contrasena, String perfil) {
        String adminEmail = DataGenerator.uniqueEmail(RegisterData.EMAIL_PREFIX);
        String testerEmail = DataGenerator.uniqueEmail(CreateUserData.EMAIL_PREFIX);

        access.enterToSystem();
        register.enterToRegister();
        register.createAdministrator(RegisterData.FIRST_NAME, RegisterData.LAST_NAME,
                adminEmail, RegisterData.PASSWORD, RegisterData.COUNTRY);
        alert.verify(AlertData.SUCCESS_TITLE, RegisterData.SUCCESS_MESSAGE);
        alert.confirm();
        login.enterToLogin();
        login.login(adminEmail, RegisterData.PASSWORD);
        alert.verify(AlertData.SUCCESS_TITLE, LoginData.SUCCESS_MESSAGE);
        alert.confirm();

        createUser.enterToCreateUser();
        createUser.verifyCreateUserPage();
        createUser.createTester(nombre, apellido, testerEmail, pais, contrasena, perfil);
        alert.verify(AlertData.SUCCESS_TITLE, CreateUserData.SUCCESS_MESSAGE);
        alert.confirm();

        users.enterToUsers();
        users.verifyUsersPage();
        users.verifyUserExists(testerEmail);
    }
}
