package com.tatf.tests;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminCESTest {

    private static final String BASE_URL =
            "http://cestore.ces.com.uy/adminces/";

    private static final String ACCESS_HASH =
            "3)ea60e0be3ba12c6ecd%7297868%5c4";

    private IBrowser browser;
    private IVerify verify;

    @BeforeEach
    void setUp() {
        browser = BrowserFactory.getBrowser(false);
        verify = IVerify.create();
        ingresarAAdminCES();
    }

    @AfterEach
    void tearDown() {
        BrowserFactory.quitBrowser();
    }

    @Test
    void crearCuentaAdministrador() {

        String emailAdmin = emailUnico("admin");
        String passwordAdmin = "Admin123";

        crearAdministrador(emailAdmin, passwordAdmin);

        verificarAlerta(
                "Correcto!",
                "Usuario creado."
        );
    }

    @Test
    void reiniciarContrasena() {

        String emailAdmin = emailUnico("admin.reset");
        String passwordInicial = "Admin123";
        String passwordNueva = "Admin456";

        crearAdministrador(emailAdmin, passwordInicial);

        verificarAlerta(
                "Correcto!",
                "Usuario creado."
        );

        cerrarAlerta();

        abrirOpcion("/adminces/forgot-password");
        browser.wait("inputEmail").name();

        browser.find().name("inputEmail").write(emailAdmin);
        browser.find().name("inputPassword").write(passwordNueva);
        browser.find().name("inputRepeatPassword").write(passwordNueva);
        browser.find().id("btnReset").click();

        verificarAlerta(
                "Correcto!",
                "Contraseña reiniciada."
        );

        cerrarAlerta();

        iniciarSesion(emailAdmin, passwordNueva);

        verificarAlerta(
                "Correcto!",
                "Sesión iniciada."
        );
    }

    @Test
    void crearCuentaTester() {

        String emailAdmin = emailUnico("admin.tester");
        String passwordAdmin = "Admin123";
        String emailTester = emailUnico("tester");

        crearAdministrador(emailAdmin, passwordAdmin);

        verificarAlerta(
                "Correcto!",
                "Usuario creado."
        );

        cerrarAlerta();

        iniciarSesion(emailAdmin, passwordAdmin);

        verificarAlerta(
                "Correcto!",
                "Sesión iniciada."
        );

        cerrarAlerta();

        crearTester(emailTester, "testerJunior");

        verificarAlerta(
                "Correcto!",
                "Usuario creado."
        );

        cerrarAlerta();

        abrirListadoUsuarios();

        verify.verifyTrue(
                !browser.find().idList(emailTester).isEmpty(),
                "El tester creado no aparece en el listado."
        );
    }

    @Test
    void eliminarCuentaTester() {

        String emailAdmin = emailUnico("admin.eliminar");
        String passwordAdmin = "Admin123";
        String emailTester = emailUnico("tester.eliminar");

        crearAdministrador(emailAdmin, passwordAdmin);

        verificarAlerta(
                "Correcto!",
                "Usuario creado."
        );

        cerrarAlerta();

        iniciarSesion(emailAdmin, passwordAdmin);

        verificarAlerta(
                "Correcto!",
                "Sesión iniciada."
        );

        cerrarAlerta();

        crearTester(emailTester, "testerSenior");

        verificarAlerta(
                "Correcto!",
                "Usuario creado."
        );

        cerrarAlerta();

        abrirListadoUsuarios();

        verify.verifyTrue(
                !browser.find().idList(emailTester).isEmpty(),
                "No se encontró el tester a eliminar."
        );

        browser.interaction().javaScriptExecutor(
                "document.getElementById('" + emailTester + "')" +
                        ".scrollIntoView({block: 'center'});"
        );

        browser.wait(1).sleep();

        browser.find().id(emailTester).click();

        verificarAlerta(
                "Pregunta!",
                "¿Eliminar usuario: " + emailTester + "?"
        );

        browser.find()
                .css("button.swal2-confirm")
                .click();

        browser.wait(1).sleep();

        verificarAlerta(
                "Correcto!",
                "Usuario eliminado."
        );

        cerrarAlerta();

        abrirListadoUsuarios();

        verify.verifyTrue(
                browser.find().idList(emailTester).isEmpty(),
                "El tester continúa apareciendo después de eliminarlo."
        );
    }

    private void ingresarAAdminCES() {

        browser.interaction().navigateTo(BASE_URL);
        browser.wait("pass").id();

        browser.find()
                .id("pass")
                .write(ACCESS_HASH);

        browser.find()
                .xpath("//button[normalize-space()='Ingresar']")
                .click();

        browser.wait(
                "//h4[normalize-space()='Accesos']"
        ).xpath();
    }

    private void crearAdministrador(
            String email,
            String password) {

        abrirOpcion("/adminces/register");
        browser.wait("inputFirstName").name();

        browser.find().name("inputFirstName").write("Admin");
        browser.find().name("inputLastName").write("Automatizado");
        browser.find().name("inputEmail").write(email);
        browser.find().name("inputPassword").write(password);
        browser.find().name("inputRepeatPassword").write(password);
        browser.find().name("inputCountry").write("Uruguay");
        browser.find().id("btnRegister").click();
    }

    private void iniciarSesion(
            String email,
            String password) {

        abrirOpcion("/adminces/login");
        browser.wait("inputEmail").name();

        browser.find().name("inputEmail").write(email);
        browser.find().name("inputPassword").write(password);

        browser.find()
                .xpath("//button[normalize-space()='Iniciar Sesión']")
                .click();
    }

    private void crearTester(
            String email,
            String perfil) {

        abrirOpcion("/adminces/create-user");
        browser.wait("inputFirstName").name();

        browser.find().name("inputFirstName").write("Tester");
        browser.find().name("inputLastName").write("Automatizado");
        browser.find().name("inputEmail").write(email);
        browser.find().name("inputCountry").selectText("Uruguay");
        browser.find().name("inputPassword").write("Tester123");

        browser.find()
                .xpath("//label[@for='" + perfil + "']")
                .click();

        browser.find().id("btnRegister").click();
    }

    private void abrirListadoUsuarios() {

        abrirOpcion("/adminces/view-users");
        browser.wait("dataTable").id();
    }

    private void abrirOpcion(String href) {

        browser.find()
                .xpath("//a[@href='" + href + "']")
                .click();
    }

    private void verificarAlerta(
            String tituloEsperado,
            String mensajeEsperado) {

        browser.wait("swal2-title").id();

        verify.verify(
                tituloEsperado,
                browser.find()
                        .id("swal2-title")
                        .getText(),
                "Título de alerta incorrecto."
        );

        verify.verify(
                mensajeEsperado,
                browser.find()
                        .id("swal2-html-container")
                        .getText(),
                "Mensaje de alerta incorrecto."
        );
    }

    private void cerrarAlerta() {

        browser.find()
                .css("button.swal2-confirm")
                .click();
        browser.wait(1).sleep();
    }

    private String emailUnico(String prefijo) {

        return prefijo
                + "."
                + System.currentTimeMillis()
                + "@test.com";
    }
}